package com.epam.jwd.repository.model.order;

import com.epam.jwd.repository.model.device.Device;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final int NANOSECONDS_TO_MILLISECONDS_RATIO = 1000000;

    private final List<Device> devices = new ArrayList<>();
    private final long orderDate; //date in milliseconds from program start
    private int telephoneNumber;


    public Order(Device device, int telephoneNumber){
        devices.add(device);
        this.telephoneNumber = telephoneNumber;
        orderDate = System.nanoTime() / NANOSECONDS_TO_MILLISECONDS_RATIO;
        System.currentTimeMillis();
    }

    public Order(List<Device> devices, int telephoneNumber){
        this.devices.addAll(devices);
        this.telephoneNumber = telephoneNumber;
        orderDate = System.nanoTime() / NANOSECONDS_TO_MILLISECONDS_RATIO;
        System.currentTimeMillis();
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public void deleteDevice(Device deviceToRemove){
        devices.remove(deviceToRemove);
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (telephoneNumber != order.telephoneNumber) return false;
        return devices != null ? devices.equals(order.devices) : order.devices == null;
    }

    @Override
    public int hashCode() {
        int result = devices != null ? devices.hashCode() : 0;
        result = 31 * result + telephoneNumber;
        return result;
    }
}
