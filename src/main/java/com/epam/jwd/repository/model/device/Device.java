package com.epam.jwd.repository.model.device;

import java.util.Objects;

public abstract class Device {
    String manufacturer;
    String model;
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (price != device.price) return false;
        if (!Objects.equals(manufacturer, device.manufacturer)) return false;
        return Objects.equals(model, device.model);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(manufacturer) + Objects.hashCode(model) +  + Objects.hashCode(price);
    }

    @Override
    public String toString() {
        return "Device " +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price;
    }
}
