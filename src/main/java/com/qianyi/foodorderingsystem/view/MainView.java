package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.model.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import com.qianyi.foodorderingsystem.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainView {

    private BorderPane mainLayout;
    private VBox centerLayout;
    private Button menuButton;
    private Button customerButton;
    private Label welcomeLabel;
    private Label loginLabel;
    private String customerName;

    private Customer currentCustomer;

    public MainView(Stage stage) {
        // Initialize the main layout
        mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));

        // Set full-screen background image
        setBackground();


        // Initialize center layout
        centerLayout = new VBox(20);
        centerLayout.setAlignment(Pos.CENTER);
        mainLayout.setCenter(centerLayout);

        // Add "Login" label in the top-left corner
        loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");
        loginLabel.setOnMouseClicked(event -> handleLoginLogout(stage));

        HBox topLayout = new HBox(loginLabel);
        topLayout.setAlignment(Pos.TOP_LEFT);
        mainLayout.setTop(topLayout);

        // Add welcome message
        welcomeLabel = new Label("Welcome to Drink Ordering System!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        centerLayout.getChildren().add(welcomeLabel);

        // Store the background image path for buttons
        String buttonBackgroundImage = getClass().getResource("/com/qianyi/foodorderingsystem/button2.png").toExternalForm();

        // Load the custom font from resources
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/qianyi/foodorderingsystem/KalamRegular.ttf"), 17);


        // Initialize buttons to navigate to other views
        menuButton = new Button("View Menu");
        customerButton = new Button("Customer Information");

        // Apply the custom font to buttons
        menuButton.setFont(customFont);
        customerButton.setFont(customFont);

        // Set button width and height
        double buttonWidth = 200;
        double buttonHeight = 40;

        menuButton.setMinWidth(buttonWidth);
        menuButton.setMaxWidth(buttonWidth);
        menuButton.setMinHeight(buttonHeight);
        menuButton.setMaxHeight(buttonHeight);

        customerButton.setMinWidth(buttonWidth);
        customerButton.setMaxWidth(buttonWidth);
        customerButton.setMinHeight(buttonHeight);
        customerButton.setMaxHeight(buttonHeight);

        // Apply the same background image to each button
        menuButton.setStyle("-fx-background-image: url('" + buttonBackgroundImage + "'); " +
                "-fx-background-size: cover; -fx-text-fill: black;");
        customerButton.setStyle("-fx-background-image: url('" + buttonBackgroundImage + "'); " +
                "-fx-background-size: cover; -fx-text-fill: black;");

        // Add buttons to the center layout
        centerLayout.getChildren().addAll(menuButton,customerButton);
    }

    // Add this method to load and set the font for welcomeLabel
    private void setCustomFont() {
        // Load the custom font from resources
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/qianyi/foodorderingsystem/KalamRegular.ttf"), 24);

        // Apply the custom font to welcomeLabel
        if (customFont != null) {
            welcomeLabel.setFont(customFont);
        } else {
            // Fallback font in case the custom font cannot be loaded
            welcomeLabel.setFont(Font.font("Arial", 24));
        }
    }

    private void setBackground() {
        // Load the image for background
        Image backgroundImage = new Image(getClass().getResource("/com/qianyi/foodorderingsystem/Background.png").toExternalForm());

        // Create a BackgroundImage object
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Repeat setting for X
                BackgroundRepeat.NO_REPEAT, // Repeat setting for Y
                BackgroundPosition.CENTER,  // Position of the background image
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
        );

        // Set the background to the main layout
        mainLayout.setBackground(new Background(background));
    }

    private void handleLoginLogout(Stage parentStage) {
        if ("Login".equals(loginLabel.getText())) {
            showLoginDialog(parentStage);
        } else if ("Logout".equals(loginLabel.getText())) {
            showLogoutConfirmation(parentStage);
        }
    }

    private void showLoginDialog(Stage parentStage) {
        // Create a new stage for the login dialog
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.initOwner(parentStage);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.setTitle("Customer Login");

        // Create input field for phone number
        Label phoneLabel = new Label("Enter your phone number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        // Create login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String phoneNumber = phoneField.getText().trim();
            if (phoneNumber.isEmpty()) {
                phoneField.clear();
                phoneLabel.setText("Phone number cannot be empty. Please try again:");
                return;
            }

            customerName = getCustomerNameByPhone(phoneNumber);
            if (customerName != null) {
                welcomeLabel.setText("Hi, " + customerName + "!" + "\nWelcome to Drink Ordering System!");
                loginLabel.setText("Logout");
                loginStage.close();
            } else {
                phoneField.clear();
                phoneLabel.setText("Phone number not found. Please try again:");
            }
        });

        // Arrange components in a layout
        VBox loginLayout = new VBox(10, phoneLabel, phoneField, loginButton);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setAlignment(Pos.CENTER);

        // Set the scene and show the stage
        Scene loginScene = new Scene(loginLayout, 330, 150);
        loginStage.setScene(loginScene);
        loginStage.showAndWait();
    }

    private void showLogoutConfirmation(Stage parentStage) {
        // Create a new stage for the logout confirmation dialog
        Stage logoutStage = new Stage();
        logoutStage.initModality(Modality.APPLICATION_MODAL);
        logoutStage.initOwner(parentStage);
        logoutStage.initStyle(StageStyle.UTILITY);
        logoutStage.setTitle("Confirm Logout");

        // Create confirmation message
        Label confirmationLabel = new Label("Are you sure you want to logout?");

        // Create Yes and No buttons
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            customerName = null;
            welcomeLabel.setText("Welcome to Drink Ordering System!");
            loginLabel.setText("Login");
            logoutStage.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> logoutStage.close());

        // Arrange components in a layout
        HBox buttonLayout = new HBox(10, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox logoutLayout = new VBox(10, confirmationLabel, buttonLayout);
        logoutLayout.setPadding(new Insets(20));
        logoutLayout.setAlignment(Pos.CENTER);

        // Set the scene and show the stage
        Scene logoutScene = new Scene(logoutLayout, 300, 150);
        logoutStage.setScene(logoutScene);
        logoutStage.showAndWait();
    }

    private String getCustomerNameByPhone(String phone) {
        String query = "SELECT name FROM customers WHERE phone = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BorderPane getMainLayout() {
        return mainLayout;
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.initOwner(mainLayout.getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Button getMenuButton() {
        return menuButton;
    }


    public Button getCustomerButton() {
        return customerButton;
    }
}
