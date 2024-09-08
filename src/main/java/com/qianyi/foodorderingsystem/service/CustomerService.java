package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing customer-related operations.
 * <p>
 * Provides methods to authenticate a customer, create, update, delete, and retrieve customers.
 * </p>
 */
public class CustomerService {
    private List<Customer> customers;

    /**
     * Constructs a {@link CustomerService} instance and initializes the customer list.
     */
    public CustomerService() {
        customers = new ArrayList<>();
    }

    /**
     * Authenticates a customer based on their email and phone number.
     * <p>
     * Queries the database to find a customer matching the provided email and phone number.
     * </p>
     *
     * @param email the email of the customer.
     * @param phone the phone number of the customer.
     * @return a {@link Customer} object if the customer is found; {@code null} otherwise.
     */
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

    /**
     * Creates a new customer and adds them to the list of customers.
     *
     * @param id the ID of the new customer.
     * @param name the name of the new customer.
     * @param contactInfo the contact information (phone number) of the new customer.
     * @param email the email of the new customer.
     * @return the newly created {@link Customer} object.
     */
    // Create New Customer
    public Customer createCustomer(int id, String name, String contactInfo, String email) {
        Customer customer = new Customer(id, name, contactInfo, email);
        customers.add(customer);
        return customer;
    }

    /**
     * Updates the information of an existing customer.
     *
     * @param customer the {@link Customer} object to be updated.
     * @param newName the new name for the customer.
     * @param newPhone the new phone number for the customer.
     * @param newEmail the new email for the customer.
     */
    // Update customer information
    public void updateCustomer(Customer customer,  String newName, String newPhone, String newEmail) {
        customer.setName(newName);
        customer.setPhone(newPhone);
        customer.setEmail(newEmail);
    }

    /**
     * Deletes a customer from the list of customers.
     *
     * @param customer the {@link Customer} object to be deleted.
     */
    // Delete Customer
    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    /**
     * Retrieves all customers.
     *
     * @return a {@link List} of {@link Customer} objects.
     */
    // Get all customers
    public List<Customer> getAllCustomers() {
        return customers;
    }
}
