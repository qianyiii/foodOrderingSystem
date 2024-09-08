package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing order-related operations.
 * <p>
 * Provides methods to retrieve available drinks, save orders, and save order items to the database.
 * </p>
 */
public class OrderService {

    /**
     * Retrieves the list of available drinks from the database.
     * <p>
     * Queries the database to get all drinks and returns them as a {@link List} of {@link Drink} objects.
     * </p>
     *
     * @return a {@link List} of {@link Drink} objects representing available drinks.
     * @throws SQLException if a database access error occurs.
     */
    // Retrieve available drinks from the database
    public List<Drink> getAvailableDrinks() throws SQLException {
        List<Drink> drinks = new ArrayList<>();
        String query = "SELECT * FROM drinks";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Drink drink = new Drink(rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getString("size"), rs.getString("category"));
                drinks.add(drink);
            }
        }

        return drinks;
    }

    /**
     * Saves an order to the database.
     * <p>
     * Inserts a new order into the orders table and retrieves the generated order ID.
     * Then, it saves the order items using the retrieved order ID.
     * </p>
     *
     * @param order the {@link Order} object to be saved.
     * @throws SQLException if a database access error occurs.
     */
    // Save order to the database
    public void saveOrder(Order order) throws SQLException {
        String orderInsertQuery = "INSERT INTO orders (customer_id, order_date) VALUES (?, NOW())";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(orderInsertQuery, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getCustomerId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    saveOrderItems(orderId, order.getDrinks());
                }
            }
        }
    }

    /**
     * Saves the items of an order to the database.
     * <p>
     * Inserts each drink in the order into the orders_items table.
     * </p>
     *
     * @param orderId the ID of the order to which the items belong.
     * @param drinks a {@link List} of {@link Drink} objects representing the items in the order.
     * @throws SQLException if a database access error occurs.
     */
    private void saveOrderItems(int orderId, List<Drink> drinks) throws SQLException {
        String orderItemInsertQuery = "INSERT INTO orders_items (order_id, drink_id, quantity, total_price) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(orderItemInsertQuery)) {

            for (Drink drink : drinks) {
                pstmt.setInt(1, orderId);
                pstmt.setInt(2, drink.getId());
                pstmt.setInt(3, 1); // Assuming quantity is 1 for simplicity
                pstmt.setDouble(4, drink.getPrice());
                pstmt.executeUpdate();
            }
        }
    }
}
