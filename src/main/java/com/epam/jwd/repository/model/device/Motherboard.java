package com.epam.jwd.repository.model.device;

import java.util.Objects;

public class Motherboard extends Device{
    private String chipset;

    private Motherboard(){}

    public String getChipset() {
        return chipset;
    }

    private void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public static class Builder {
        String chipset;
        String manufacturer;
        String model;
        int price;

        public Builder withManufacturer(String manufacturer){
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder withModel(String model){
            this.model = model;
            return this;
        }

        public Builder withPrice(int price){
            this.price = price;
            return this;
        }

        public Builder withChipset(String chipset){
            this.chipset = chipset;
            return this;
        }

        public Motherboard build(){
            Motherboard motherboard = new Motherboard();
            motherboard.setChipset(chipset);
            motherboard.setManufacturer(manufacturer);
            motherboard.setModel(model);
            motherboard.setPrice(price);
            return motherboard;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Motherboard that = (Motherboard) o;

        return Objects.equals(chipset, that.chipset) && super.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(chipset) + super.hashCode();
    }


    @Override
    public String toString() {
        return "Motherboard " +
                "manufacturer='" + manufacturer +
                ", model='" + model +
                ", price=" + price +
                ", chipset='" + chipset;
    }
}
