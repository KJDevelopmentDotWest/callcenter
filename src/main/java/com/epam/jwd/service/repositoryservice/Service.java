package com.epam.jwd.service.repositoryservice;

import com.epam.jwd.repository.model.device.Device;
import com.epam.jwd.repository.model.order.Order;
import com.epam.jwd.repository.storage.Repository;
import com.epam.jwd.service.concurrent.ConcurrencyProvider;
import com.epam.jwd.service.exception.WrongThreadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Service {
    private static final Logger logger = LogManager.getLogger(Service.class);

    private static final String LOGGER_MESSAGE = "Wrong thread Exception";
    private static final String MAIN_THREAD_NAME = "main";

    private final Repository repository = Repository.getInstance();
    private final ConcurrencyProvider concurrencyProvider = ConcurrencyProvider.getInstance();

    private static Service instance = null;

    private Service(){

    }

    public static Service getInstance(){
        if (instance == null){
            instance = new Service();
        }
        return instance;
    }

    public synchronized void addOrderToRepository(Order order){
        if (MAIN_THREAD_NAME.equals(Thread.currentThread().getName())){
            logger.error(LOGGER_MESSAGE);
            throw new WrongThreadException(Thread.currentThread().getName());
        }
        repository.addOrder(order);
    }

    public synchronized List<Device> getDevices(){
        return repository.getDevices();
    }

    public void addOrderToRepository(List<Device> devices, int telephoneNumber){
        if (MAIN_THREAD_NAME.equals(Thread.currentThread().getName())){
            logger.error(LOGGER_MESSAGE);
            throw new WrongThreadException(Thread.currentThread().getName());
        }
        addOrderToRepository(new Order(devices, telephoneNumber));
    }

    public void addCustomer(){
        concurrencyProvider.addCustomer();
    }

    //MUST be called at the end of program
    public void exit(){
        concurrencyProvider.exit();
    }
}
