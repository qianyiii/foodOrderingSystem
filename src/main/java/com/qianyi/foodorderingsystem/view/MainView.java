package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.service.OrderService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Button orderButton;
    private Button customerButton;
    private Label welcomeLabel;
    private Label loginLabel;
    private String customerName;

    private OrderController orderController;
    private Customer currentCustomer;

    public MainView(Stage stage) {
        // Initialize the main layout
        mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));

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

        // Initialize buttons to navigate to other views
        menuButton = new Button("View Menu");
        orderButton = new Button("Place Order");
        customerButton = new Button("Customer Information");

        // Add buttons to the center layout
        centerLayout.getChildren().addAll(menuButton, orderButton, customerButton);

        // Initialize OrderController
        orderController = new OrderController(new OrderService());

        // Attach event handlers
        orderButton.setOnAction(event -> navigateToOrderView());
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
            String phoneNumber = phoneField.getText();
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
        Scene loginScene = new Scene(loginLayout, 300, 150);
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

    private void navigateToOrderView() {
        if (currentCustomer != null) {
            orderController.createNewOrder(currentCustomer);
            OrderView orderView = new OrderView(orderController);
            Stage orderStage = new Stage();
            orderStage.setTitle("Order View");
            orderStage.initOwner(mainLayout.getScene().getWindow());
            orderStage.setScene(new Scene(orderView.getOrderLayout(), 500, 600));
            orderStage.show();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Customer", "Please log in before placing an order.");
        }
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

    public Button getOrderButton() {
        return orderButton;
    }

    public Button getCustomerButton() {
        return customerButton;
    }
}
