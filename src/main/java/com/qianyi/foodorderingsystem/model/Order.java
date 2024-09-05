package com.qianyi.foodorderingsystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private LocalDateTime orderDate;
    private List<Drink> drinks; // Add a list to store drinks

    public Order() {
        drinks = new ArrayList<>(); // Initialize the list of drinks
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // Add drink to the order
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Remove drink from the order
    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    // Get all drinks in the order
    public List<Drink> getDrinks() {
        return drinks;
    }
}
