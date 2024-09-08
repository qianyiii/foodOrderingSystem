package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Menu;
import com.qianyi.foodorderingsystem.service.MenuService;

/**
 * Controller class for managing menu-related operations.
 * <p>
 * Provides methods to add or remove drinks from the menu, retrieve the current menu, and display it.
 * </p>
 */
public class MenuController {
    private MenuService menuService;

    /**
     * Constructs a {@link MenuController} instance with the specified {@link MenuService}.
     *
     * @param menuService the {@link MenuService} instance used by this controller.
     */
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * Adds a drink to the menu.
     *
     * @param drink the {@link Drink} object to be added to the menu.
     */
    // Add drinks to the menu
    public void addDrinkToMenu(Drink drink) {
        menuService.addDrinkToMenu(drink);
    }

    /**
     * Removes a drink from the menu.
     *
     * @param drink the {@link Drink} object to be removed from the menu.
     */
    // Remove drinks from the menu
    public void removeDrinkFromMenu(Drink drink) {
        menuService.removeDrinkFromMenu(drink);
    }

    /**
     * Retrieves the current menu.
     *
     * @return the {@link Menu} object representing the current menu.
     */
    // Get the current menu
    public Menu getMenu() {
        return menuService.getMenu();
    }

    /**
     * Displays the current menu.
     * <p>
     * Prints the menu to the standard output.
     * </p>
     */
    // Show menu
    public void displayMenu() {
        menuService.displayMenu();
    }
}
