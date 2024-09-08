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

public class OrderController {
    private Map<Drink, Integer> orderItems = new HashMap<>();

    private OrderService orderService;
    private Order currentOrder;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Drink> getAvailableDrinks() throws SQLException {
        return orderService.getAvailableDrinks();
    }

    public void addDrinkToOrder(Drink drink) {
        if (orderItems.containsKey(drink)) {
            orderItems.put(drink, orderItems.get(drink) + 1);
        } else {
            orderItems.put(drink, 1);
        }
    }

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

    public Map<Drink, Integer> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Drink, Integer> entry : orderItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }


    public void saveOrder() throws SQLException {
        if (currentOrder != null) {
            orderService.saveOrder(currentOrder);
        }
    }

    public void createNewOrder(Customer customer) {
        currentOrder = new Order();
        currentOrder.setCustomerId(customer.getCustomerId());
    }
}