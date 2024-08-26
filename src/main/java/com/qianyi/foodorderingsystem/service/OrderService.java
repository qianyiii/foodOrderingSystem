package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    // 创建新订单
    public Order createOrder(Order order) {
        orders.add(order);
        return order;
    }

    // 添加饮料到订单
    public void addDrinkToOrder(Order order, Drink drink) {
        order.addDrink(drink);
    }

    // 从订单中删除饮料
    public void removeDrinkFromOrder(Order order, Drink drink) {
        order.removeDrink(drink);
    }

    // 计算订单总价格
    public double calculateTotalPrice(Order order) {
        return order.calculateTotalPrice();
    }

    // 获取所有订单
    public List<Order> getAllOrders() {
        return orders;
    }

    // 根据订单号查找订单
    public Order findOrderById(int orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }
}
