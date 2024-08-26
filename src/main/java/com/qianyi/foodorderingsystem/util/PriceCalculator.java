package com.qianyi.foodorderingsystem.util;

import com.qianyi.foodorderingsystem.model.Drink;

import java.util.List;

public class PriceCalculator {

    private static final double TAX_RATE = 0.07; // 7% tax rate
    private static final double DISCOUNT_RATE = 0.1; // 10% discount

    // Method to calculate the total price of a list of drinks
    public static double calculateTotalPrice(List<Drink> drinks, boolean applyDiscount) {
        double total = 0.0;

        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        // Apply discount if applicable
        if (applyDiscount) {
            total *= (1 - DISCOUNT_RATE);
        }

        // Apply tax
        total *= (1 + TAX_RATE);

        return total;
    }
}

