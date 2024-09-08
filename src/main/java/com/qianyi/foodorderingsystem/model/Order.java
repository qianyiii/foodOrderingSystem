package com.qianyi.foodorderingsystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the food ordering system.
 * <p>
 * This class contains details about an order, including the customer who placed it, the date of the order, and the drinks included in the order.
 * </p>
 */
public class Order {
    private int id;
    private int customerId;
    private LocalDateTime orderDate;
    private List<Drink> drinks; // Add a list to store drinks

    /**
     * Constructs an {@link Order} object with an empty list of drinks.
     */
    public Order() {
        drinks = new ArrayList<>(); // Initialize the list of drinks
    }

    /**
     * Retrieves the unique identifier of the order.
     *
     * @return the order ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the order.
     *
     * @param id the order ID to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier of the customer who placed the order.
     *
     * @return the customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier of the customer who placed the order.
     *
     * @param customerId the customer ID to be set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the date and time when the order was placed.
     *
     * @return the order date and time.
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the date and time when the order was placed.
     *
     * @param orderDate the order date and time to be set.
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Adds a drink to the order.
     *
     * @param drink the drink to be added to the order.
     */
    // Add drink to the order
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    /**
     * Removes a drink from the order.
     *
     * @param drink the drink to be removed from the order.
     */
    // Remove drink from the order
    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    /**
     * Retrieves the list of drinks in the order.
     *
     * @return the list of drinks.
     */
    // Get all drinks in the order
    public List<Drink> getDrinks() {
        return drinks;
    }
}
