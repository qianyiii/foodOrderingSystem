package com.qianyi.foodorderingsystem.model;
/**
 * Represents a customer in the food ordering system.
 * <p>
 * This class holds information about a customer, including their ID, name, phone number, and email.
 * </p>
 */
public class Customer {
    private Integer customerId;
    private String name;
    private String phone;
    private String email;

    /**
     * Constructs a {@link Customer} object with the specified attributes.
     *
     * @param customerId the unique ID of the customer.
     * @param name the name of the customer.
     * @param phone the phone number of the customer.
     * @param email the email address of the customer.
     */
    public Customer(Integer customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Retrieves the unique ID of the customer.
     *
     * @return the customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return the customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the new name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the customer.
     *
     * @return the customer's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone the new phone number to be set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address of the customer.
     *
     * @return the customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email the new email address to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the customer.
     * <p>
     * The string includes the customer ID, name, phone number, and email address.
     * </p>
     *
     * @return a string representation of the customer.
     */
    @Override
    public String toString() {
        return "Id: " + customerId + " Customer: " + name + " (" + phone + ") Email: " + email;
    }
}
