package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 创建新顾客
    public Customer createCustomer(String name, String contactInfo) {
        return customerService.createCustomer(name, contactInfo);
    }

    // 更新顾客信息
    public void updateCustomer(Customer customer, String newName, String newContactInfo) {
        customerService.updateCustomer(customer, newName, newContactInfo);
    }

    // 删除顾客
    public void deleteCustomer(Customer customer) {
        customerService.deleteCustomer(customer);
    }

    // 获取所有顾客
    public void getAllCustomers() {
        customerService.getAllCustomers().forEach(System.out::println);
    }
}
