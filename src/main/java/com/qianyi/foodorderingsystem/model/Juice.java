package com.qianyi.foodorderingsystem.model;

/**
 * Represents a juice drink in the food ordering system, which extends {@link Drink}.
 * <p>
 * This class adds a specific attribute for juice drinks, which is the type of fruit used.
 * </p>
 */
public class Juice extends Drink {
    private String fruitType;

    /**
     * Constructs a {@link Juice} object with the specified attributes.
     *
     * @param id the unique ID of the juice.
     * @param name the name of the juice.
     * @param price the price of the juice.
     * @param size the size of the juice.
     * @param category the category of the juice.
     * @param fruitType the type of fruit used in the juice.
     */
    public Juice(int id, String name, double price, String size, String category, String fruitType) {
        super(id, name, price, size, category);
        this.fruitType = fruitType;
    }

    /**
     * Retrieves the type of fruit used in the juice.
     *
     * @return the fruit type.
     */
    public String getFruitType() {
        return fruitType;
    }

    /**
     * Sets the type of fruit used in the juice.
     *
     * @param fruitType the new fruit type to be set.
     */
    public void setFruitType(String fruitType) {
        this.fruitType = fruitType;
    }

    /**
     * Returns a string representation of the juice.
     * <p>
     * The string includes the juice's name, size, price, and fruit type.
     * </p>
     *
     * @return a string representation of the juice.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + fruitType;
    }
}
