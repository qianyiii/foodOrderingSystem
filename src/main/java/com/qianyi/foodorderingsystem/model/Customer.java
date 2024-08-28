package com.qianyi.foodorderingsystem.model;

public class Customer {
    private int id;
    private String name;
    private String contactInfo;
    private String email;

    public Customer(int id, String name, String contactInfo, String email) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id: " + id + "Customer: " + name + " (" + contactInfo + ")" + "Email: " + email;
    }
}
