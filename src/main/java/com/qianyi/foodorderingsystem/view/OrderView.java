package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.model.Drink;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class OrderView {
    private OrderController orderController;
    private VBox orderLayout;
    private ListView<Drink> drinkListView;
    private ListView<Drink> orderListView;
    private Label totalPriceLabel;
    private Button addButton;
    private Button removeButton;
    private Button confirmButton;

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

        // Available drinks list
        drinkListView = new ListView<>();
        drinkListView.setPrefHeight(200);

        // Order items list
        orderListView = new ListView<>();
        orderListView.setPrefHeight(200);

        // Buttons
        addButton = new Button("Add >>");
        addButton.setOnAction(e -> {
            Drink selectedDrink = drinkListView.getSelectionModel().getSelectedItem();
            if (selectedDrink != null) {
                orderController.addDrinkToOrder(selectedDrink);
                orderListView.getItems().add(selectedDrink);
                updateTotalPrice();
            }
        });
        removeButton = new Button("<< Remove");
        removeButton.setOnAction(e -> {
            Drink selectedDrink = orderListView.getSelectionModel().getSelectedItem();
            if (selectedDrink != null) {
                orderController.removeDrinkFromOrder(selectedDrink);
                orderListView.getItems().remove(selectedDrink);
                updateTotalPrice();
            }
        });
        confirmButton = new Button("Confirm Order");
        confirmButton.setOnAction(e -> {
            try {
                orderController.saveOrder();
                showAlert(Alert.AlertType.INFORMATION, "Order Confirmed", "Your order has been placed successfully!");
                Stage stage = (Stage) orderLayout.getScene().getWindow();
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Order Failed", "Failed to save order to the database.");
            }
        });

        totalPriceLabel = new Label("Total Price: RM0.00");

        orderLayout.getChildren().addAll(
                title,
                new Label("Available Drinks:"),
                drinkListView,
                addButton,
                new Label("Your Order:"),
                orderListView,
                removeButton,
                totalPriceLabel,
                confirmButton
        );

        // Load drinks data
        try {
            List<Drink> drinks = orderController.getAvailableDrinks();
            drinkListView.getItems().addAll(drinks);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Data Load Error", "Failed to load available drinks.");
        }
    }

    public void addDrinkToOrder(Drink drink) {
        if (drink != null && drink.getId() > 0) {
            orderController.addDrinkToOrder(drink);
            orderListView.getItems().add(drink);
            updateTotalPrice();
        } else {
            showAlert(Alert.AlertType.WARNING, "Invalid Drink", "Selected drink has an invalid ID.");
        }
    }

    private void removeDrinkFromOrder() {
        Drink selectedDrink = orderListView.getSelectionModel().getSelectedItem();
        if (selectedDrink != null) {
            orderController.removeDrinkFromOrder(selectedDrink);
            orderListView.getItems().remove(selectedDrink);
            updateTotalPrice();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a drink to remove.");
        }
    }

    private void updateTotalPrice() {
        double totalPrice = orderController.getTotalPrice();
        totalPriceLabel.setText(String.format("Total Price: RM%.2f", totalPrice));
    }

    private void confirmOrder() {
        try {
            orderController.saveOrder(); // This may throw SQLException
            showAlert(Alert.AlertType.INFORMATION, "Order Confirmed", "Your order has been placed successfully!");
            // Close the order window after confirmation
            Stage stage = (Stage) orderLayout.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Order Failed", "Failed to save order to the database.");
        }
    }

    public VBox getOrderLayout() {
        return orderLayout;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.initOwner(orderLayout.getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


