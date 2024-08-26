package com.qianyi.foodorderingsystem.model;

public class Store {
    private String name;
    private String address;
    private String businessHours;

    public Store(String name, String address, String businessHours) {
        this.name = name;
        this.address = address;
        this.businessHours = businessHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    @Override
    public String toString() {
        return name + " located at " + address + " (Open: " + businessHours + ")";
    }
}
