package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CustomerView {

    private VBox customerLayout;
    private TextField nameField;
    private TextField phoneField;

    public CustomerView() {
        // Initialize the layout
        customerLayout = new VBox(20);
        customerLayout.setPadding(new Insets(20));
        customerLayout.setAlignment(Pos.CENTER);

        // Add title
        Label customerLabel = new Label("Customer Information");
        customerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Text fields for customer information
        nameField = new TextField();
        nameField.setPromptText("Enter your name");

        phoneField = new TextField();
        phoneField.setPromptText("Enter your phone number");

        // Button to save customer information
        Button saveButton = new Button("Save Information");

        // Add components to the layout
        customerLayout.getChildren().addAll(customerLabel, nameField, phoneField, saveButton);
    }

    public VBox getCustomerLayout() {
        return customerLayout;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }
}
