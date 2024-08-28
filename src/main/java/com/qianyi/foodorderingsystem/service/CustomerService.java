package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    // 创建新顾客
    public Customer createCustomer(int id, String name, String contactInfo, String email) {
        Customer customer = new Customer(id, name, contactInfo, email);
        customers.add(customer);
        return customer;
    }

    // 更新顾客信息
    public void updateCustomer(Customer customer,  String newName, String newContactInfo, String newEmail) {
        customer.setName(newName);
        customer.setContactInfo(newContactInfo);
        customer.setEmail(newEmail);
    }

    // 删除顾客
    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    // 获取所有顾客
    public List<Customer> getAllCustomers() {
        return customers;
    }
}
