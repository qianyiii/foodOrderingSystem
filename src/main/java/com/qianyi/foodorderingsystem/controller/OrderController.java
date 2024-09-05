package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.service.OrderService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderController {

    private OrderService orderService;
    private Order currentOrder;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Drink> getAvailableDrinks() throws SQLException {
        return orderService.getAvailableDrinks();
    }

    public void addDrinkToOrder(Drink drink) {
        currentOrder.addDrink(drink);
    }

    public void removeDrinkFromOrder(Drink drink) {
        currentOrder.removeDrink(drink);
    }

    public double getTotalPrice() {
        return currentOrder.getDrinks().stream().mapToDouble(Drink::getPrice).sum();
    }

    public void saveOrder() throws SQLException {
        orderService.saveOrder(currentOrder);
    }

    public void createNewOrder(Customer customer) {
        currentOrder = new Order();
        currentOrder.setCustomerId(customer.getCustomerId());
    }
}