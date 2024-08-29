package com.qianyi.foodorderingsystem.model;

public class Drink {
    private String name;
    private double price;
    private String size;

    public Drink(String name, double price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return name + " (" + size + " - RM" + price;
    }
}
