package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.service.OrderService;

public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 创建新订单
    public Order createOrder() {
        Order order = new Order();
        return orderService.createOrder(order);
    }

    // 添加饮料到订单
    public void addDrinkToOrder(Order order, Drink drink) {
        orderService.addDrinkToOrder(order, drink);
    }

    // 删除订单中的饮料
    public void removeDrinkFromOrder(Order order, Drink drink) {
        orderService.removeDrinkFromOrder(order, drink);
    }

    // 获取订单总价格
    public double getTotalPrice(Order order) {
        return orderService.calculateTotalPrice(order);
    }

    // 查找订单
    public Order findOrderById(int orderId) {
        return orderService.findOrderById(orderId);
    }
}
