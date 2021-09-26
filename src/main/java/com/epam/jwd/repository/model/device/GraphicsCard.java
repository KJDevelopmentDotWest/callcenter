package com.epam.jwd.repository.model.device;

import java.util.Objects;

public class GraphicsCard extends Device {
    private int videoCoresAmount;
    private int memoryCapacity;

    public int getVideoCoresAmount() {
        return videoCoresAmount;
    }

    private void setVideoCoresAmount(int videoCoresAmount) {
        this.videoCoresAmount = videoCoresAmount;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    private void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public static class Builder {
        String manufacturer;
        String model;
        int price;
        int videoCoresAmount;
        int memoryCapacity;

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

        public Builder withVideoCoresAmount(int videoCoresAmount) {
            this.videoCoresAmount = videoCoresAmount;
            return this;
        }

        public Builder withMemoryCapacity(int memoryCapacity) {
            this.memoryCapacity = memoryCapacity;
            return this;
        }

        public GraphicsCard build() {
            GraphicsCard graphicsCard = new GraphicsCard();
            graphicsCard.setManufacturer(manufacturer);
            graphicsCard.setModel(model);
            graphicsCard.setPrice(price);
            graphicsCard.setVideoCoresAmount(videoCoresAmount);
            graphicsCard.setMemoryCapacity(memoryCapacity);
            return graphicsCard;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GraphicsCard that = (GraphicsCard) o;

        if (videoCoresAmount != that.videoCoresAmount) return false;
        return memoryCapacity == that.memoryCapacity && super.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(videoCoresAmount) + Objects.hashCode(memoryCapacity) + super.hashCode();
    }

    @Override
    public String toString() {
        return "GraphicsCard " +
                "manufacturer='" + manufacturer +
                ", model='" + model +
                ", price=" + price +
                ", videoCoresAmount=" + videoCoresAmount +
                ", memoryCapacity=" + memoryCapacity;
    }
}
