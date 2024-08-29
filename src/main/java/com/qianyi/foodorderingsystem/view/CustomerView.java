package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerView {

    private BorderPane customerLayout;
    private TextField nameField;
    private TextField phoneField;
    private TextField emailField;

    public CustomerView(Stage stage, Scene mainScene) {
        // Initialize the layout
        customerLayout = new BorderPane();
        customerLayout.setPadding(new Insets(20));

        // Create the "Back" label
        Label backLabel = new Label("Back");
        backLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");
        backLabel.setOnMouseClicked(event -> stage.setScene(mainScene));  // Go back to the main view on click

        // Position the "Back" label at the top-left corner
        HBox backBox = new HBox(backLabel);
        backBox.setAlignment(Pos.TOP_LEFT);
        customerLayout.setTop(backBox);

        // Add title
        Label customerLabel = new Label("Customer Information Form");
        customerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Text fields for customer information
        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setPrefWidth(350);

        phoneField = new TextField();
        phoneField.setPromptText("Enter your phone number");
        phoneField.setPrefWidth(350);

        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setPrefWidth(350);

        // Create labels and HBox containers
        HBox nameBox = createLabeledTextField("Name:", nameField);
        HBox phoneBox = createLabeledTextField("Contact No:", phoneField);
        HBox emailBox = createLabeledTextField("Email:", emailField);

        // Button to submit customer information
        Button submitButton = new Button("Submit");

        // Add components to the layout
        VBox formLayout = new VBox(20, customerLabel, nameBox, phoneBox, emailBox, submitButton);
        formLayout.setAlignment(Pos.CENTER);
        customerLayout.setCenter(formLayout);
    }

    private HBox createLabeledTextField(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setPrefWidth(100);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(label, textField);
        return hBox;
    }

    public BorderPane getCustomerLayout() {
        return customerLayout;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public TextField getEmailField() {
        return emailField;
    }
}
