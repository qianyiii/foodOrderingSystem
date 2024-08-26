package com.qianyi.foodorderingsystem.util;

public class ValidationUtil {

    // Method to validate a string (e.g., non-null and non-empty)
    public static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Method to validate a number (e.g., positive number)
    public static boolean isValidNumber(double number) {
        return number > 0;
    }
}

