package com.qianyi.foodorderingsystem.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file operations.
 * <p>
 * Provides methods to write data to a file and read data from a file.
 * </p>
 */
public class FileUtil {

    /**
     * Writes the given order summary to a file. If the file already exists,
     * the data is appended to the end of the file.
     *
     * @param filePath the path of the file where the order summary will be written.
     * @param orderSummary the order summary to be written to the file.
     */
    // Method to write data to a file
    public static void writeOrderToFile(String filePath, String orderSummary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(orderSummary);
            writer.newLine(); // Add a newline for each new order
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from a file and returns it as a list of strings, where each
     * string represents a line from the file.
     *
     * @param filePath the path of the file to be read.
     * @return a {@link List} of strings, each representing a line from the file.
     */
    // Method to read data from a file
    public static List<String> readFromFile(String filePath) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return data;
    }
}
