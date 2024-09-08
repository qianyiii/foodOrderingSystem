package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    public static Customer authenticateCustomer(String email, String phone) {
        String query = "SELECT * FROM customers WHERE email = ? AND phone = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, phone);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return null; // Customer not found
    }

    // 创建新顾客
    public Customer createCustomer(int id, String name, String contactInfo, String email) {
        Customer customer = new Customer(id, name, contactInfo, email);
        customers.add(customer);
        return customer;
    }

    // 更新顾客信息
    public void updateCustomer(Customer customer,  String newName, String newPhone, String newEmail) {
        customer.setName(newName);
        customer.setPhone(newPhone);
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
