package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Menu;

/**
 * Service class for managing menu-related operations.
 * <p>
 * Provides methods to add or remove drinks from the menu, retrieve the menu, and display it.
 * </p>
 */
public class MenuService {
    private Menu menu;

    /**
     * Constructs a {@link MenuService} instance and initializes the menu.
     */
    public MenuService() {
        this.menu = new Menu();
    }

    /**
     * Adds a drink to the menu.
     *
     * @param drink the {@link Drink} object to be added to the menu.
     */
    // Add drinks to the menu
    public void addDrinkToMenu(Drink drink) {
        menu.addDrink(drink);
    }

    /**
     * Removes a drink from the menu.
     *
     * @param drink the {@link Drink} object to be removed from the menu.
     */
    // Remove drinks from the menu
    public void removeDrinkFromMenu(Drink drink) {
        menu.removeDrink(drink);
    }

    /**
     * Retrieves the current menu.
     *
     * @return the {@link Menu} object representing the current menu.
     */
    // Get Menu
    public Menu getMenu() {
        return menu;
    }

    /**
     * Displays the current menu.
     * <p>
     * Prints the menu to the standard output.
     * </p>
     */
    // Show menu
    public void displayMenu() {
        System.out.println(menu);
    }
}
