package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

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
