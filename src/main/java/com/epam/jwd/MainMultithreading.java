package com.epam.jwd;

//this main demonstrating multiple users behaviour

//index out of bounds exceptions will happen cause all threads use one InputStream. there is nothing I can do about this, also there will be no problem if each customer will use his own InputStream

import com.epam.jwd.service.repositoryservice.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainMultithreading {

    private static final Logger logger = LogManager.getLogger(MainMultithreading.class);
    private final ThreadPoolExecutor customers = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) {
        MainMultithreading main = new MainMultithreading();
        main.start();
    }

    private void start(){
        customers.execute(new CustomerSimulatorThread());
        customers.execute(new CustomerSimulatorThread());
        customers.execute(new CustomerSimulatorThread());
        customers.execute(new CustomerSimulatorThread());
        customers.execute(new CustomerSimulatorThread());
        customers.execute(new CustomerSimulatorThread());
        customers.execute(new CustomerSimulatorThread());
    }

    public class CustomerSimulatorThread implements Runnable{

        @Override
        public void run() {
            Service.getInstance().addCustomer();

            int counter = 0;

            while (counter < 15){
                counter++;
                simulateRandomButton();
                try {
                    Thread.sleep((System.nanoTime() % 1000) + 500);
                } catch (InterruptedException e) {
                    logger.error(e);
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void simulateRandomButton(){
            try {
                Robot robot = new Robot();
                int randomNumKey = (int) ((System.nanoTime() % 9) + 48);
                robot.keyPress(randomNumKey);
                robot.keyRelease(randomNumKey);
                robot.keyPress(KeyEvent.VK_ENTER);
            } catch (AWTException e) {
                logger.error(e);
            }
        }
    }
}
