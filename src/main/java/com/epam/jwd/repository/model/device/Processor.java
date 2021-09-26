package com.epam.jwd.repository.model.device;

import java.util.Objects;

public class Processor extends Device{
    private int coreAmount;
    private int clock;

    private Processor(){};

    public int getCoreAmount() {
        return coreAmount;
    }

    private void setCoreAmount(int coreAmount) {
        this.coreAmount = coreAmount;
    }

    public int getClock() {
        return clock;
    }

    private void setClock(int clock) {
        this.clock = clock;
    }

    public static class Builder {
        String manufacturer;
        String model;
        int price;
        int coreAmount;
        int clock;

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

        public Builder withCoreAmount(int coreAmount) {
            this.coreAmount = coreAmount;
            return this;
        }

        public Builder withClock(int clock) {
            this.clock = clock;
            return this;
        }


        public Processor build() {
            Processor processor = new Processor();
            processor.setManufacturer(manufacturer);
            processor.setModel(model);
            processor.setPrice(price);
            processor.setCoreAmount(coreAmount);
            processor.setClock(clock);
            return processor;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Processor that = (Processor) o;

        if (coreAmount != that.coreAmount) return false;
        return clock == that.clock && super.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coreAmount) + Objects.hashCode(clock) + super.hashCode();
    }

    @Override
    public String toString() {
        return "Processor " +
                "manufacturer='" + manufacturer +
                ", model='" + model +
                ", price=" + price +
                ", clock=" + clock;
    }
}
