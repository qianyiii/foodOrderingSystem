package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.util.FileUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * This class represents the Order View for a food ordering system.
 * It allows the user to view the current order, remove items, and confirm the order.
 *
 * It interacts with the {@link OrderController} to manage drinks in the order and calculate the total price.
 */
public class OrderView {
    private OrderController orderController;
    private VBox orderLayout;
    private ListView<OrderItem> orderListView;
    private Label totalPriceLabel;
    private Button removeButton;
    private Button confirmButton;
    private static final String ORDER_HISTORY_FILE = "order_history.txt"; // Specify your file path


    /**
     * Constructs an OrderView instance with a given OrderController.
     * Initializes the layout and loads the drinks data.
     *
     * @param orderController the controller managing the order
     */
    public OrderView(OrderController orderController) {
        this.orderController = orderController;
        initializeLayout();
    }

    private void initializeLayout() {
        orderLayout = new VBox(10);
        orderLayout.setPadding(new Insets(20));
        orderLayout.setAlignment(Pos.CENTER);

        Text title = new Text("Place Your Order");
        title.setFont(Font.font(20));

        // Order items list
        orderListView = new ListView<>();
        orderListView.setPrefHeight(500);

        // Buttons
        removeButton = new Button("<< Remove");
        removeButton.setOnAction(e -> removeDrinkFromOrder());

        confirmButton = new Button("Confirm Order");
        confirmButton.setOnAction(e -> confirmOrder());

        totalPriceLabel = new Label("Total Price: RM0.00");

        orderLayout.getChildren().addAll(
                title,
                new Label("Your Order:"),
                orderListView,
                removeButton,
                totalPriceLabel,
                confirmButton
        );

        orderListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(OrderItem item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        // Load drinks data
        try {
            List<Drink> drinks = orderController.getAvailableDrinks();
            // You should have a way to set drinks in the view if necessary
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Data Load Error", "Failed to load available drinks.");
        }

        updateOrderList();
        updateTotalPrice();
    }

    /**
     * Adds a drink to the order.
     *
     * @param drink the drink to be added
     */
    public void addDrinkToOrder(Drink drink) {
        if (drink != null && drink.getId() > 0) {
            orderController.addDrinkToOrder(drink);
            updateOrderList();
            updateTotalPrice();
        } else {
            showAlert(Alert.AlertType.WARNING, "Invalid Drink", "Selected drink has an invalid ID.");
        }
    }

    /**
     * Removes the selected drink from the order.
     */
    private void removeDrinkFromOrder() {
        OrderItem selectedItem = orderListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Drink selectedDrink = selectedItem.getDrink();
            orderController.removeDrinkFromOrder(selectedDrink);
            updateOrderList();
            updateTotalPrice();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a drink to remove.");
        }
    }

    /**
     * Updates the order list view with the current items in the order.
     */
    private void updateOrderList() {
        orderListView.getItems().clear();
        for (Map.Entry<Drink, Integer> entry : orderController.getOrderItems().entrySet()) {
            orderListView.getItems().add(new OrderItem(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * Updates the total price label with the current total.
     */
    private void updateTotalPrice() {
        double totalPrice = orderController.getTotalPrice();
        totalPriceLabel.setText(String.format("Total Price: RM%.2f", totalPrice));
    }

    /**
     * Confirms the order and saves it to the database.
     * Also saves the order summary to a file.
     */
    private void confirmOrder() {
        if (orderListView.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Order", "Your order list is empty. Please add items to your order before confirming.");
            return;
        }

        try {
            orderController.saveOrder(); // This may throw SQLException

            // Display order details in a new alert
            String orderSummary = getOrderSummary();
            showAlert(Alert.AlertType.INFORMATION, "Order Confirmed", orderSummary);

            // Save order summary to file
            FileUtil.writeOrderToFile(ORDER_HISTORY_FILE, orderSummary);

            // Optionally clear the order view or reset for the next order
            orderListView.getItems().clear();
            updateTotalPrice();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Order Failed", "Failed to save order to the database.");
        }
    }

    /**
     * Generates a summary of the current order.
     *
     * @return the summary of the order as a String
     */
    private String getOrderSummary() {
        StringBuilder summary = new StringBuilder("Order Summary:\n\n");

        for (OrderItem item : orderListView.getItems()) {
            summary.append(String.format("%s (x%d) - RM%.2f\n", item.getDrink().getName(), item.getQuantity(), item.getSubtotal()));
        }

        summary.append(String.format("\nTotal Price: RM%.2f", orderController.getTotalPrice()));

        return summary.toString();
    }

    public VBox getOrderLayout() {
        return orderLayout;
    }

    /**
     * Displays an alert message to the user.
     *
     * @param alertType the type of alert (e.g., ERROR, WARNING)
     * @param title the title of the alert
     * @param message the message content of the alert
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.initOwner(orderLayout.getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Class representing an order item, which contains a drink and its quantity.
     */
    private class OrderItem {
        private Drink drink;
        private int quantity;

        /**
         * Constructs an OrderItem with a drink and its quantity.
         *
         * @param drink the drink in the order
         * @param quantity the quantity of the drink
         */
        public OrderItem(Drink drink, int quantity) {
            this.drink = drink;
            this.quantity = quantity;
        }

        /**
         * Gets the drink associated with this order item.
         *
         * @return the drink
         */
        public Drink getDrink() {
            return drink;
        }

        /**
         * Gets the quantity of the drink in the order.
         *
         * @return the quantity of the drink
         */
        public int getQuantity() {
            return quantity;
        }

        /**
         * Calculates the subtotal for the order item.
         *
         * @return the subtotal for this item
         */
        public double getSubtotal() {
            return drink.getPrice() * quantity;
        }

        /**
         * Returns a string representation of the order item.
         *
         * @return a string containing the drink name, quantity, and subtotal
         */
        @Override
        public String toString() {
            return String.format("%s (x%d) - RM%.2f", drink.getName(), quantity, getSubtotal());
        }
    }
}
