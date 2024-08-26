package com.qianyi.foodorderingsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/drinks_db";
    private static final String USER = "root";
    private static final String PASSWORD = "pa55word";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    // Method to execute an update (e.g., INSERT, UPDATE, DELETE) query
    public static void executeUpdate(String query, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
        }
    }
}
