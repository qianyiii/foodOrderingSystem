package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.service.CustomerService;

/**
 * Controller class for managing customer-related operations.
 * <p>
 * Provides methods to create, update, delete, and retrieve customers.
 * </p>
 */
public class CustomerController {
    private CustomerService customerService;

    /**
     * Constructs a {@link CustomerController} instance with the specified {@link CustomerService}.
     *
     * @param customerService the {@link CustomerService} instance used by this controller.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Creates a new customer.
     *
     * @param id the ID of the new customer.
     * @param name the name of the new customer.
     * @param contactInfo the contact information of the new customer.
     * @param email the email of the new customer.
     * @return the newly created {@link Customer} object.
     */
    // Create New Customer
    public Customer createCustomer(int id, String name, String contactInfo, String email) {
        return customerService.createCustomer(id, name, contactInfo, email);
    }

    /**
     * Updates the information of an existing customer.
     *
     * @param customer the {@link Customer} object to be updated.
     * @param newName the new name for the customer.
     * @param newContactInfo the new contact information for the customer.
     * @param newEmail the new email for the customer.
     */
    // Update customer information
    public void updateCustomer(Customer customer, String newName, String newContactInfo, String newEmail) {
        customerService.updateCustomer(customer, newName, newContactInfo, newEmail);
    }

    /**
     * Deletes a customer.
     *
     * @param customer the {@link Customer} object to be deleted.
     */
    // Delete Customer
    public void deleteCustomer(Customer customer) {
        customerService.deleteCustomer(customer);
    }

    /**
     * Retrieves and prints all customers.
     */
    // Get all customers
    public void getAllCustomers() {
        customerService.getAllCustomers().forEach(System.out::println);
    }
}
