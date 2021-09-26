package com.epam.jwd.service.concurrent;

import com.epam.jwd.controller.Controller;
import com.epam.jwd.repository.model.device.Device;
import com.epam.jwd.service.repositoryservice.Service;
import com.epam.jwd.view.API.Performer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerThread implements Runnable{
    private static final Logger logger = LogManager.getLogger(CustomerThread.class);

    private static final int USER_CALLBACK_NEGATIVE = Integer.MIN_VALUE;
    private final Future<Integer> callbackFuture;
    private final ConcurrencyProvider concurrencyProvider;
    private final Controller controller;
    private final Map<Integer, Performer> optionToPerformerMap;
    private final Service service = Service.getInstance();
    private final ExecutorService singleThreadPool =  Executors.newSingleThreadExecutor();

    private final List<Device> currentOrderDevices = new ArrayList<>();
    private int telephoneNumber;

    CustomerThread(){
        concurrencyProvider =  ConcurrencyProvider.getInstance();
        controller = new Controller();

        optionToPerformerMap = new HashMap<>();
        optionToPerformerMap.put(1, this::addDevice);
        optionToPerformerMap.put(2, this::removeDevice);
        optionToPerformerMap.put(3, this::addTelephoneNumber);
        optionToPerformerMap.put(4, this::printDefaultDevices);
        optionToPerformerMap.put(5, this::printCurrentOrderDevices);
        optionToPerformerMap.put(6, this::exitAndSave);
        optionToPerformerMap.put(7, this::exitAndDisband);

        callbackFuture = singleThreadPool.submit(new ConsoleInputReadThread());
    }

    @Override
    public void run() {
        waitForFreeThreadOrUserCallback();

        int callback = USER_CALLBACK_NEGATIVE;
        if (callbackFuture.isDone()){
            try {
                callback = callbackFuture.get();
            } catch (InterruptedException e) {
                logger.error(e);
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                logger.error(e);
            }
        } else {
            callbackFuture.cancel(true);
        }

        singleThreadPool.shutdown();

        if (callback != 1){
            concurrencyProvider.incrementBusyOperatorCount();
            conversationStart();
            concurrencyProvider.decrementBusyOperatorCount();
        }
    }

    private void waitForFreeThreadOrUserCallback(){
        controller.waitStartMessage();
        while (!concurrencyProvider.checkPermission() && !callbackFuture.isDone()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private void conversationStart(){
        controller.callStartMessage();
        conversationBody();
    }

    private void conversationBody(){
        controller.printOptionsMenu();
        optionToPerformerMap.getOrDefault(controller.readUserIntInput(), this::invalidInputHandler).execute();
    }

    private void addDevice(){
        printDefaultDevices();
        int callback = controller.readUserIntInput();
        List<Device> defaultDevices = service.getDevices();
        if (callback < 1 || callback > defaultDevices.size()){
            invalidInputHandler();
        }
        currentOrderDevices.add(service.getDevices().get(callback));
        conversationBody();
    }

    private void addTelephoneNumber(){
        telephoneNumber = controller.readUserIntInput();
        conversationBody();
    }

    private void removeDevice(){
        printCurrentOrderDevices();
        int callback = controller.readUserIntInput();
        if (callback < 1 || callback > currentOrderDevices.size()){
            invalidInputHandler();
        }
        currentOrderDevices.remove(callback);
        conversationBody();
    }

    private void printDefaultDevices(){
        controller.printMessage(generateDevicesListMenu(service.getDevices()));
    }

    private void printCurrentOrderDevices(){
        controller.printMessage(generateDevicesListMenu(currentOrderDevices));
    }

    private void invalidInputHandler(){
        controller.printInvalidInputMessage();
        conversationBody();
    }

    private void exitAndSave(){
        service.addOrderToRepository(currentOrderDevices, telephoneNumber);
        service.exit();
    }

    private void exitAndDisband(){
        service.exit();
    }

    private String generateDevicesListMenu(List<Device> devices){
        AtomicInteger counter = new AtomicInteger(1);
        StringBuilder stringBuilder = new StringBuilder();
        devices.forEach(device -> {
            stringBuilder.append(counter.get()).append(". ").append(device).append("\n");
            counter.getAndIncrement();
        });
        return stringBuilder.toString();
    }

    private class ConsoleInputReadThread implements Callable<Integer>{

        @Override
        public Integer call() {
            String input;
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            do {
                try {
                    while (!br.ready()) {
                        Thread.sleep(200);
                    }
                    input = br.readLine();
                } catch (InterruptedException | IOException e) {
                    return null;
                }
            } while ("".equals(input));
            return Integer.valueOf(input);
        }
    }
}
