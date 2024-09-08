package com.qianyi.foodorderingsystem.util;

/**
 * Utility class for validation of strings and numbers.
 * <p>
 * Provides methods to check if a string is valid (non-null and non-empty) and if a number is positive.
 * </p>
 */
public class ValidationUtil {

    /**
     * Validates a string to ensure it is non-null and non-empty.
     * <p>
     * A string is considered valid if it is not {@code null} and its trimmed value is not empty.
     * </p>
     *
     * @param value the string to be validated.
     * @return {@code true} if the string is non-null and non-empty; {@code false} otherwise.
     */
    // Method to validate a string (e.g., non-null and non-empty)
    public static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validates a number to ensure it is positive.
     * <p>
     * A number is considered valid if it is greater than zero.
     * </p>
     *
     * @param number the number to be validated.
     * @return {@code true} if the number is greater than zero; {@code false} otherwise.
     */
    // Method to validate a number (e.g., positive number)
    public static boolean isValidNumber(double number) {
        return number > 0;
    }
}

