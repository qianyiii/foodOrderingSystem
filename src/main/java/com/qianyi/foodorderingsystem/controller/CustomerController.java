package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 创建新顾客
    public Customer createCustomer(int id, String name, String contactInfo, String email) {
        return customerService.createCustomer(id, name, contactInfo, email);
    }

    // 更新顾客信息
    public void updateCustomer(Customer customer, String newName, String newContactInfo, String newEmail) {
        customerService.updateCustomer(customer, newName, newContactInfo, newEmail);
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
