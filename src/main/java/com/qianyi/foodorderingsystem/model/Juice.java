package com.qianyi.foodorderingsystem.model;

public class Juice extends Drink {
    private String fruitType;

    public Juice(String name, double price, String size, String fruitType) {
        super(name, price, size);
        this.fruitType = fruitType;
    }

    public String getFruitType() {
        return fruitType;
    }

    public void setFruitType(String fruitType) {
        this.fruitType = fruitType;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + fruitType;
    }
}
