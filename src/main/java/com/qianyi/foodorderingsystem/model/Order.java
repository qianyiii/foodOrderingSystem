package com.qianyi.foodorderingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private Customer customer;
    private List<Drink> drinks;

    // Constructor with Customer
    public Order(Customer customer) {
        this.orderId = nextOrderId++;
        this.customer = customer;
        this.drinks = new ArrayList<>();
    }

    // Default constructor
    public Order() {
        this.orderId = nextOrderId++;
        this.drinks = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        String customerName = (customer != null) ? customer.getName() : "No customer assigned";
        return "Order ID: " + orderId + "\nCustomer: " + customerName + "\nTotal Price: RM" + calculateTotalPrice();
    }
}
