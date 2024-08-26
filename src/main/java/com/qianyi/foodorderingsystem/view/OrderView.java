package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class OrderView {

    private VBox orderLayout;
    private ListView<String> orderListView;

    public OrderView() {
        // Initialize the layout
        orderLayout = new VBox(20);
        orderLayout.setPadding(new Insets(20));
        orderLayout.setAlignment(Pos.CENTER);

        // Add title
        Label orderLabel = new Label("Your Order");
        orderLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // List to display selected drinks
        orderListView = new ListView<>();

        // Button to confirm order
        Button confirmButton = new Button("Confirm Order");

        // Add components to the layout
        orderLayout.getChildren().addAll(orderLabel, orderListView, confirmButton);
    }

    public VBox getOrderLayout() {
        return orderLayout;
    }

    public ListView<String> getOrderListView() {
        return orderListView;
    }
}

