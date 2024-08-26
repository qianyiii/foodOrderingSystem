package com.qianyi.foodorderingsystem.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    // Method to write data to a file
    public static void writeToFile(String filePath, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

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
