package com.epam.jwd.service.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyProvider {

    private static final int MAX_OPERATORS_COUNT = 5;
    private final ThreadPoolExecutor customerThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private static ConcurrencyProvider instance = null;
    private int currentBusyOperatorsCount = 0;
    private final ReentrantLock lock = new ReentrantLock();

    private ConcurrencyProvider(){}

    public static ConcurrencyProvider getInstance(){
        if (instance == null){
            instance = new ConcurrencyProvider();
        }
        return instance;
    }

    public void addCustomer(){
        customerThreadPool.execute(new CustomerThread());
    }

    synchronized void incrementBusyOperatorCount(){
        currentBusyOperatorsCount++;
    }

    synchronized void decrementBusyOperatorCount(){
        currentBusyOperatorsCount--;
    }

    boolean checkPermission(){
        lock.lock();
        boolean flag = MAX_OPERATORS_COUNT > currentBusyOperatorsCount;
        lock.unlock();
        return flag;
    }

    public void exit(){
        customerThreadPool.shutdown();
    }

}
