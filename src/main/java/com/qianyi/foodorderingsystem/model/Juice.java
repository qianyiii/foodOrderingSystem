package com.qianyi.foodorderingsystem.model;

public class Juice extends Drink {
    private String fruitType;

    public Juice(int id, String name, double price, String size, String category, String fruitType) {
        super(id, name, price, size, category);
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
