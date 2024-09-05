package com.qianyi.foodorderingsystem.model;

public class Tea extends Drink {
    private String teaType;

    public Tea(int id, String name, double price, String size, String category, String teaType) {
        super(id, name, price, size, category);
        this.teaType = teaType;
    }

    public String getTeaType() {
        return teaType;
    }

    public void setTeaType(String teaType) {
        this.teaType = teaType;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + teaType;
    }
}
