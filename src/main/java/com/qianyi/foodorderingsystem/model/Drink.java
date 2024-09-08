package com.qianyi.foodorderingsystem.model;

/**
 * Represents a drink item in the food ordering system.
 * <p>
 * This class contains details about a drink, including its ID, name, price, size, and category.
 * </p>
 */
public class Drink {
    private int id;
    private String name;
    private double price;
    private String size;
    private String category;

    /**
     * Constructs a {@link Drink} object with the specified attributes.
     *
     * @param id the unique ID of the drink.
     * @param name the name of the drink.
     * @param price the price of the drink.
     * @param size the size of the drink.
     * @param category the category of the drink.
     */
    public Drink(int id, String name, double price, String size, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.category = category;
    }

    // Getters and setters

    /**
     * Retrieves the unique ID of the drink.
     *
     * @return the drink ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the drink.
     *
     * @param id the new ID to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the drink.
     *
     * @return the drink's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the drink.
     *
     * @param name the new name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the drink.
     *
     * @return the drink's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the drink.
     *
     * @param price the new price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the size of the drink.
     *
     * @return the drink's size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the drink.
     *
     * @param size the new size to be set.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Retrieves the category of the drink.
     *
     * @return the drink's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns a string representation of the drink.
     * <p>
     * The string includes the drink's name, size, and price.
     * </p>
     *
     * @return a string representation of the drink.
     */
    @Override
    public String toString() {
        return name + " (" + size + " )" + " - RM" + price;
    }


}
