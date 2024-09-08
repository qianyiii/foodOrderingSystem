package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Drink;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing drink-related operations.
 * <p>
 * Provides methods to create, update, delete, and retrieve drinks.
 * </p>
 */
public class DrinkService {
    private List<Drink> drinks;

    /**
     * Constructs a {@link DrinkService} instance and initializes the drink list.
     */
    public DrinkService() {
        drinks = new ArrayList<>();
    }

    /**
     * Creates a new drink and adds it to the list of drinks.
     *
     * @param drink the {@link Drink} object to be created.
     * @return the newly created {@link Drink} object.
     */
    // Create new drinks
    public Drink createDrink(Drink drink) {
        drinks.add(drink);
        return drink;
    }

    /**
     * Updates the information of an existing drink.
     *
     * @param drink the {@link Drink} object to be updated.
     * @param newName the new name for the drink.
     * @param newPrice the new price for the drink.
     * @param newSize the new size for the drink.
     */
    // Update beverage information
    public void updateDrink(Drink drink, String newName, double newPrice, String newSize) {
        drink.setName(newName);
        drink.setPrice(newPrice);
        drink.setSize(newSize);
    }

    /**
     * Deletes a drink from the list of drinks.
     *
     * @param drink the {@link Drink} object to be deleted.
     */
    // Remove Drinks
    public void deleteDrink(Drink drink) {
        drinks.remove(drink);
    }

    /**
     * Retrieves all drinks.
     *
     * @return a {@link List} of {@link Drink} objects.
     */
    // Get all drinks
    public List<Drink> getAllDrinks() {
        return drinks;
    }
}
