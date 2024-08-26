package com.qianyi.foodorderingsystem.model;

public class Coffee extends Drink {
    private String roastLevel;

    public Coffee(String name, double price, String size, String roastLevel) {
        super(name, price, size);
        this.roastLevel = roastLevel;
    }

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + roastLevel + " roast";
    }
}
