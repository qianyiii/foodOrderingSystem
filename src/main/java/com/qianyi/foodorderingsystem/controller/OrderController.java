package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.service.OrderService;

public class OrderController {
    private OrderService orderService;
    private Order currentOrder;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        this.currentOrder = new Order();  // Use default constructor
    }

    public Order createOrder() {
        return orderService.createOrder(new Order());  // Use default constructor
    }

    public void addDrinkToOrder(Order order, Drink drink) {
        orderService.addDrinkToOrder(order, drink);
    }

    public void removeDrinkFromOrder(Order order, Drink drink) {
        orderService.removeDrinkFromOrder(order, drink);
    }

    public double getTotalPrice(Order order) {
        return orderService.calculateTotalPrice(order);
    }

    public Order findOrderById(int orderId) {
        return orderService.findOrderById(orderId);
    }

    public void setCustomerForOrder(Order order, Customer customer) {
        order.setCustomer(customer);
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void updateOrder() {
        // Notify or update views as needed
        if (currentOrder.getDrinks().isEmpty()) {
            System.out.println("Order is empty.");
        } else {
            System.out.println("Order updated. Total items: " + currentOrder.getDrinks().size());
        }
    }
}
