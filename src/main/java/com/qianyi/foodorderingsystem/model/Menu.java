package com.qianyi.foodorderingsystem.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a menu containing a list of drinks.
 * <p>
 * This class manages the collection of drinks available in the menu, allowing addition, removal, and retrieval of drinks.
 * </p>
 */
public class Menu {
    private List<Drink> drinks;

    /**
     * Constructs a {@link Menu} object with an empty list of drinks.
     */
    public Menu() {
        drinks = new ArrayList<>();
    }

    /**
     * Adds a drink to the menu.
     *
     * @param drink the drink to be added to the menu.
     */
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    /**
     * Removes a drink from the menu.
     *
     * @param drink the drink to be removed from the menu.
     */
    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    /**
     * Retrieves the list of drinks in the menu.
     *
     * @return the list of drinks.
     */
    public List<Drink> getDrinks() {
        return drinks;
    }

    /**
     * Returns a string representation of the menu.
     * <p>
     * The string includes the list of drinks in the menu, each on a new line.
     * </p>
     *
     * @return a string representation of the menu.
     */
    @Override
    public String toString() {
        StringBuilder menuStr = new StringBuilder("Menu:\n");
        for (Drink drink : drinks) {
            menuStr.append(drink.toString()).append("\n");
        }
        return menuStr.toString();
    }
}
