package com.qianyi.foodorderingsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Utility class for handling database connections and executing SQL updates.
 * <p>
 * Provides methods to establish a connection to a MySQL database, close the connection,
 * and execute SQL update operations (INSERT, UPDATE, DELETE) with parameters.
 * </p>
 */
public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/drinks_db";
    private static final String USER = "root";
    private static final String PASSWORD = "pa55word";

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return a {@link Connection} object representing the database connection.
     * @throws SQLException if a database access error occurs or the URL is incorrect.
     */
    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Closes the specified database connection.
     *
     * @param connection the {@link Connection} object to be closed. If the connection is null, this method does nothing.
     */
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

    /**
     * Executes an SQL update query (e.g., INSERT, UPDATE, DELETE) with the specified parameters.
     * <p>
     * The SQL query should be provided as a string, with placeholders (?) for the parameters.
     * The parameters are set in the order they appear in the query.
     * </p>
     *
     * @param query  the SQL update query to be executed.
     * @param params the parameters to be set in the query.
     */
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
