package com.epam.jwd.repository.storage;

import com.epam.jwd.repository.model.device.*;
import com.epam.jwd.repository.model.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository {

    private static Repository instance = null;
    private final List<Device> devices = new ArrayList<>();
    private final List<Order> storage = new ArrayList<>();

    private Repository(){
        devices.add(new Motherboard.Builder()
                .withPrice(100)
                .withChipset("Intel Z690")
                .withManufacturer("Asus")
                .withModel("Max Pro")
                .build());

        devices.add(new Motherboard.Builder()
                .withPrice(70)
                .withChipset("AMD B460")
                .withManufacturer("MSI")
                .withModel("Gaming X")
                .build());

        devices.add(new Processor.Builder()
                .withPrice(250)
                .withManufacturer("Intel")
                .withModel("I9 11900k")
                .withClock(5300)
                .withCoreAmount(10)
                .build());

        devices.add(new Processor.Builder()
                .withPrice(150)
                .withManufacturer("AMD")
                .withModel("Ryzen 7 3700x")
                .withClock(4600)
                .withCoreAmount(8)
                .build());

        devices.add(new Memory.Builder()
                .withPrice(50)
                .withManufacturer("Crucial")
                .withModel("Ballistix")
                .withCapacity(16)
                .build());

        devices.add(new Memory.Builder()
                .withPrice(110)
                .withManufacturer("HyperX")
                .withModel("Fury")
                .withCapacity(32)
                .build());

        devices.add(new GraphicsCard.Builder()
                .withPrice(110)
                .withManufacturer("Nvidia")
                .withModel("RTX 3090")
                .withVideoCoresAmount(10496)
                .withMemoryCapacity(24)
                .build());

        devices.add(new GraphicsCard.Builder()
                .withPrice(110)
                .withManufacturer("AMD")
                .withModel("RX 6900 XT")
                .withVideoCoresAmount(5120)
                .withMemoryCapacity(16)
                .build());
    }

    public static Repository getInstance(){
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void addOrder(Order order){
        storage.add(order);
    }

    public void removeOrder(Order order){
        storage.remove(order);
    }

    public List<Device> getDevices(){
        return Collections.unmodifiableList(devices);
    }

    public List<Order> gerOrders(){
        return storage;
    }

}
