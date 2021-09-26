package com.epam.jwd.service.repositoryservice;

import com.epam.jwd.repository.model.order.Order;
import com.epam.jwd.repository.storage.Repository;
import com.epam.jwd.service.concurrent.ConcurrencyProvider;
import com.epam.jwd.service.exception.WrongThreadException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private static final String MAIN_THREAD_NAME = "main";

    private Service service = Service.getInstance();
    private Repository repository = Repository.getInstance();

    @Test
    public void addToRepositoryTest(){
        String oldThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(MAIN_THREAD_NAME);
        assertThrows(WrongThreadException.class, () -> service.addOrderToRepository(new Order(new ArrayList<>(), 10)));
        Thread.currentThread().setName(oldThreadName);
    }

    @Test
    public void getDevicesTest(){
        assertArrayEquals(repository.getDevices().toArray(), service.getDevices().toArray());
    }

}