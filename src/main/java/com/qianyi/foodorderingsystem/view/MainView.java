package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainView {

    private VBox mainLayout;
    private Button menuButton;
    private Button orderButton;
    private Button customerButton;

    public MainView() {
        // Initialize the main layout
        mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        // Add welcome message
        Label welcomeLabel = new Label("Welcome to Drink Ordering System!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Initialize buttons to navigate to other views
        menuButton = new Button("View Menu");
        orderButton = new Button("Place Order");
        customerButton = new Button("Customer Information");

        // Add components to the layout
        mainLayout.getChildren().addAll(welcomeLabel, menuButton, orderButton, customerButton);
    }

    public VBox getMainLayout() {
        return mainLayout;
    }

    public Button getMenuButton() {
        return menuButton;
    }

    public Button getOrderButton() {
        return orderButton;
    }

    public Button getCustomerButton() {
        return customerButton;
    }
}
