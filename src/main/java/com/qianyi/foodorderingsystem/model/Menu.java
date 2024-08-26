package com.qianyi.foodorderingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Drink> drinks;

    public Menu() {
        drinks = new ArrayList<>();
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    @Override
    public String toString() {
        StringBuilder menuStr = new StringBuilder("Menu:\n");
        for (Drink drink : drinks) {
            menuStr.append(drink.toString()).append("\n");
        }
        return menuStr.toString();
    }
}
