package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.util.DatabaseUtil;
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
        submitButton.setOnAction(e -> {
            insertCustomerData(nameField.getText(), phoneField.getText(), emailField.getText());
            showSuccessMessage(stage);
        });

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

    private void insertCustomerData(String name, String phone, String email) {
        // Define the SQL query
        String query = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";

        // Use DatabaseUtil to execute the query
        DatabaseUtil.executeUpdate(query, name, phone, email);
    }

    private void showSuccessMessage(Stage parentStage) {
        // Create a new stage for the success message
        Stage messageStage = new Stage();
        messageStage.setTitle("Success");

        // Labels to display the customer information
        Label messageLabel = new Label("Your information has been submitted.");
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Name: " + nameField.getText());
        Label phoneLabel = new Label("Contact No: " + phoneField.getText());
        Label emailLabel = new Label("Email: " + emailField.getText());

        // Create the "OK" button
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            // Clear the text fields
            nameField.clear();
            phoneField.clear();
            emailField.clear();
            // Close the message window
            messageStage.close();
        });

        // Arrange message and button in a layout
        VBox messageLayout = new VBox(20, messageLabel, nameLabel, phoneLabel, emailLabel, okButton);
        messageLayout.setAlignment(Pos.CENTER);
        messageLayout.setPadding(new Insets(20));

        // Set up the scene and stage
        Scene scene = new Scene(messageLayout, 450, 200);
        messageStage.setScene(scene);
        messageStage.initOwner(parentStage); // Set the parent window
        messageStage.showAndWait(); // Block the parent window until this one is closed
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
