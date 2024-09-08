package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.util.DatabaseUtil;
import com.qianyi.foodorderingsystem.util.ValidationUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.regex.Pattern;

/**
 * The CustomerView class represents the UI for submitting customer information
 * such as name, phone number, and email in the Drink Ordering System.
 * It provides validation for input data, background image styling,
 * and interacts with the database to insert customer records.
 */
public class CustomerView {

    private BorderPane customerLayout;
    private TextField nameField;
    private TextField phoneField;
    private TextField emailField;

    /**
     * Constructs a CustomerView object.
     *
     * @param stage the main application stage
     * @param mainScene the main scene to return to
     */
    public CustomerView(Stage stage, Scene mainScene) {
        // Initialize the layout
        customerLayout = new BorderPane();
        customerLayout.setPadding(new Insets(20));

        // Set full-screen background image
        setBackgroundImage("/com/qianyi/foodorderingsystem/Background3.png");

        // Create the "Back" label
        Label backLabel = new Label("<- Back");
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
            if (isValidCustomerData(nameField.getText(), phoneField.getText(), emailField.getText())) {
                insertCustomerData(nameField.getText(), phoneField.getText(), emailField.getText());
                showSuccessMessage(stage);
            }
        });

        // Add components to the layout
        VBox formLayout = new VBox(20, customerLabel, nameBox, phoneBox, emailBox, submitButton);
        formLayout.setAlignment(Pos.CENTER);
        customerLayout.setCenter(formLayout);
    }

    /**
     * Creates an HBox layout for a labeled TextField.
     *
     * @param labelText the text for the label
     * @param textField the TextField associated with the label
     * @return a configured HBox containing the label and TextField
     */
    private HBox createLabeledTextField(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setPrefWidth(100);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(label, textField);
        return hBox;
    }

    /**
     * Inserts customer data into the database.
     *
     * @param name the customer's name
     * @param phone the customer's phone number
     * @param email the customer's email address
     */
    private void insertCustomerData(String name, String phone, String email) {
        // Define the SQL query
        String query = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";

        // Use DatabaseUtil to execute the query
        DatabaseUtil.executeUpdate(query, name, phone, email);
    }

    /**
     * Displays a success message with the entered customer information.
     *
     * @param parentStage the parent stage for the message dialog
     */
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

    /**
     * Sets a background image for the view.
     *
     * @param imagePath the path to the background image
     */
    private void setBackgroundImage(String imagePath) {
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        customerLayout.setBackground(new Background(backgroundImage));
    }

    /**
     * Validates the customer's name, phone number, and email format.
     *
     * @param name the customer's name
     * @param phone the customer's phone number
     * @param email the customer's email
     * @return true if the data is valid, false otherwise
     */
    private boolean isValidCustomerData(String name, String phone, String email) {
        if (!ValidationUtil.isValidString(name) || !ValidationUtil.isValidString(phone) || !ValidationUtil.isValidString(email)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return false;
        }
        if (!isValidName(name)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Name can only contain English letters.");
            return false;
        }
        if (!isValidPhoneNumber(phone)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Phone number must be numeric.");
            return false;
        }
        if (!isValidEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid email format.");
            return false;
        }
        return true;
    }

    /**
     * Validates the customer's name to ensure it contains only letters.
     *
     * @param name the customer's name
     * @return true if valid, false otherwise
     */
    private boolean isValidName(String name) {
        return Pattern.matches("[a-zA-Z]+", name);
    }

    /**
     * Validates the phone number to ensure it contains only digits.
     *
     * @param phone the customer's phone number
     * @return true if valid, false otherwise
     */
    private boolean isValidPhoneNumber(String phone) {
        return Pattern.matches("\\d+", phone);
    }

    /**
     * Validates the email format.
     *
     * @param email the customer's email
     * @return true if valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        // Basic email pattern, adjust as needed
        return Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", email);
    }

    /**
     * Displays an alert with the specified type, title, and message.
     *
     * @param alertType the type of alert (e.g., ERROR, INFORMATION)
     * @param title the alert title
     * @param message the message content
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.initOwner(customerLayout.getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Returns the main customer layout.
     *
     * @return the main layout as a BorderPane
     */
    public BorderPane getCustomerLayout() {
        return customerLayout;
    }

    /**
     * Returns the TextField for the customer's name.
     *
     * @return the name TextField
     */
    public TextField getNameField() {
        return nameField;
    }

    /**
     * Returns the TextField for the customer's phone number.
     *
     * @return the phone TextField
     */
    public TextField getPhoneField() {
        return phoneField;
    }

    /**
     * Returns the TextField for the customer's email.
     *
     * @return the email TextField
     */
    public TextField getEmailField() {
        return emailField;
    }
}
