package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainView {

    private VBox mainLayout;

    public MainView() {
        // Initialize the main layout
        mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        // Add welcome message
        Label welcomeLabel = new Label("Welcome to Fauget Fresh Drink Ordering System!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Add buttons to navigate to other views
        Button menuButton = new Button("View Menu");
        Button orderButton = new Button("Place Order");
        Button customerButton = new Button("Customer Information");

        // Add components to the layout
        mainLayout.getChildren().addAll(welcomeLabel, menuButton, orderButton, customerButton);
    }

    public VBox getMainLayout() {
        return mainLayout;
    }
}
