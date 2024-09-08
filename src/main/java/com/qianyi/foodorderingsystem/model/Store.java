package com.qianyi.foodorderingsystem.model;

/**
 * Represents a store in the food ordering system.
 * <p>
 * This class holds information about the store's name, address, and business hours.
 * </p>
 */
public class Store {
    private String name;
    private String address;
    private String businessHours;

    /**
     * Constructs a {@link Store} object with the specified details.
     *
     * @param name          the name of the store.
     * @param address       the address of the store.
     * @param businessHours the business hours of the store.
     */
    public Store(String name, String address, String businessHours) {
        this.name = name;
        this.address = address;
        this.businessHours = businessHours;
    }

    /**
     * Retrieves the name of the store.
     *
     * @return the store name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the store.
     *
     * @param name the name to be set for the store.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the store.
     *
     * @return the store address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the store.
     *
     * @param address the address to be set for the store.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the business hours of the store.
     *
     * @return the store's business hours.
     */
    public String getBusinessHours() {
        return businessHours;
    }

    /**
     * Sets the business hours of the store.
     *
     * @param businessHours the business hours to be set for the store.
     */
    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    /**
     * Returns a string representation of the {@link Store} object.
     * The string includes the store's name, address, and business hours.
     *
     * @return a string representation of the store.
     */
    @Override
    public String toString() {
        return name + " located at " + address + " (Open: " + businessHours + ")";
    }
}
