package com.qianyi.foodorderingsystem.model;

/**
 * Represents a type of tea in the food ordering system.
 * <p>
 * This class extends the {@link Drink} class to include additional details specific to tea.
 * </p>
 */
public class Tea extends Drink {
    private String teaType;

    /**
     * Constructs a {@link Tea} object with the specified details.
     *
     * @param id         the unique identifier for the tea.
     * @param name       the name of the tea.
     * @param price      the price of the tea.
     * @param size       the size of the tea.
     * @param category   the category of the tea.
     * @param teaType    the type of tea (e.g., black, green, herbal).
     */
    public Tea(int id, String name, double price, String size, String category, String teaType) {
        super(id, name, price, size, category);
        this.teaType = teaType;
    }

    /**
     * Retrieves the type of tea.
     *
     * @return the tea type.
     */
    public String getTeaType() {
        return teaType;
    }

    /**
     * Sets the type of tea.
     *
     * @param teaType the tea type to be set.
     */
    public void setTeaType(String teaType) {
        this.teaType = teaType;
    }

    /**
     * Returns a string representation of the {@link Tea} object, including the type of tea.
     *
     * @return a string representation of the tea.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + teaType;
    }
}
