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
        if (currentOrder != null) {
            currentOrder.addDrink(drink);
        }
    }

    public void removeDrinkFromOrder(Drink drink) {
        if (currentOrder != null) {
            currentOrder.removeDrink(drink);
        }
    }

    public double getTotalPrice() {
        if (currentOrder != null) {
            return currentOrder.getDrinks().stream().mapToDouble(Drink::getPrice).sum();
        }
        return 0.0;
    }

    public List<Drink> getOrderItems() {
        if (currentOrder != null) {
            return currentOrder.getDrinks();
        }
        return List.of(); // 如果没有当前订单，返回一个空的列表
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