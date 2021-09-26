package com.epam.jwd.repository.model.device;

import java.util.Objects;

public class Memory extends Device{
    private int capacity;

    private Memory(){}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static class Builder {
        String manufacturer;
        String model;
        int price;
        int capacity;

        public Builder withManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Memory build() {
            Memory memory = new Memory();
            memory.setManufacturer(manufacturer);
            memory.setModel(model);
            memory.setPrice(price);
            memory.setCapacity(capacity);
            return memory;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Memory that = (Memory) o;

        return capacity == that.capacity && super.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(capacity) + super.hashCode();
    }

    @Override
    public String toString() {
        return "Memory " +
                "manufacturer='" + manufacturer +
                ", model='" + model +
                ", price=" + price +
                ", capacity=" + capacity;
    }
}
