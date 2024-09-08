package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.service.OrderService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing order-related operations.
 * <p>
 * Provides methods to add or remove drinks from an order, retrieve order items, calculate total price,
 * and save the order.
 * </p>
 */
public class OrderController {
    private Map<Drink, Integer> orderItems = new HashMap<>();

    private OrderService orderService;
    private Order currentOrder;

    /**
     * Constructs an {@link OrderController} instance with the specified {@link OrderService}.
     *
     * @param orderService the {@link OrderService} instance used by this controller.
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves the list of available drinks.
     *
     * @return a {@link List} of {@link Drink} objects representing available drinks.
     * @throws SQLException if a database access error occurs.
     */
    public List<Drink> getAvailableDrinks() throws SQLException {
        return orderService.getAvailableDrinks();
    }

    /**
     * Adds a drink to the current order.
     *
     * @param drink the {@link Drink} object to be added to the order.
     */
    public void addDrinkToOrder(Drink drink) {
        if (orderItems.containsKey(drink)) {
            orderItems.put(drink, orderItems.get(drink) + 1);
        } else {
            orderItems.put(drink, 1);
        }
    }

    /**
     * Removes a drink from the current order.
     *
     * @param drink the {@link Drink} object to be removed from the order.
     */
    public void removeDrinkFromOrder(Drink drink) {
        if (orderItems.containsKey(drink)) {
            int currentQuantity = orderItems.get(drink);
            if (currentQuantity > 1) {
                orderItems.put(drink, currentQuantity - 1);
            } else {
                orderItems.remove(drink);
            }
        }
    }

    /**
     * Retrieves the current order items.
     *
     * @return a {@link Map} of {@link Drink} objects and their quantities in the current order.
     */
    public Map<Drink, Integer> getOrderItems() {
        return orderItems;
    }

    /**
     * Calculates the total price of the current order.
     *
     * @return the total price of the order.
     */
    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Drink, Integer> entry : orderItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Saves the current order to the database.
     *
     * @throws SQLException if a database access error occurs.
     */
    public void saveOrder() throws SQLException {
        if (currentOrder != null) {
            orderService.saveOrder(currentOrder);
        }
    }

    /**
     * Creates a new order for the specified customer.
     *
     * @param customer the {@link Customer} object for which the new order is created.
     */
    public void createNewOrder(Customer customer) {
        currentOrder = new Order();
        currentOrder.setCustomerId(customer.getCustomerId());
    }
}