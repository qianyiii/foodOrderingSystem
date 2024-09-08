package com.qianyi.foodorderingsystem.model;

/**
 * Represents a coffee drink, which extends from {@link Drink}.
 * <p>
 * Adds an additional attribute to specify the roast level of the coffee.
 * </p>
 */
public class Coffee extends Drink {
    private String roastLevel;

    /**
     * Constructs a {@link Coffee} object with the specified attributes.
     *
     * @param id the ID of the coffee.
     * @param name the name of the coffee.
     * @param price the price of the coffee.
     * @param size the size of the coffee.
     * @param category the category of the coffee.
     * @param roastLevel the roast level of the coffee.
     */
    public Coffee(int id, String name, double price, String size,String category, String roastLevel) {
        super(id, name, price, size, category);
        this.roastLevel = roastLevel;
    }

    /**
     * Retrieves the roast level of the coffee.
     *
     * @return the roast level of the coffee.
     */
    public String getRoastLevel() {
        return roastLevel;
    }

    /**
     * Sets the roast level of the coffee.
     *
     * @param roastLevel the new roast level to be set.
     */
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    /**
     * Returns a string representation of the coffee, including the roast level.
     * <p>
     * This method overrides {@link Drink#toString()} to include roast level in the output.
     * </p>
     *
     * @return a string representation of the coffee.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + roastLevel + " roast";
    }
}
