package com.qianyi.foodorderingsystem;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.service.OrderService;
import com.qianyi.foodorderingsystem.view.CustomerView;
import com.qianyi.foodorderingsystem.view.MainView;
import com.qianyi.foodorderingsystem.view.MenuView;
import com.qianyi.foodorderingsystem.view.OrderView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DrinkOrderingSystem extends Application {

    private MenuView menuView;
    private OrderService orderService;
    private OrderController orderController;

    @Override
    public void start(Stage stage) {
        // Initialize the OrderService and OrderController
        orderService = new OrderService();
        orderController = new OrderController(orderService);

        // Load the image from resources
        Image image = new Image(getClass().getResourceAsStream("Cover Image.png"));
        ImageView imageView = new ImageView(image);

        // Make the image cover the entire window and resize with the window
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(stage.getWidth());
        imageView.setFitHeight(stage.getHeight());

        // Bind image view size to stage size
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());

        // Create "Start to Order Now!" label
        Label startLabel = new Label("Start to Order Now!");
        startLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: black;");
        startLabel.setOnMouseClicked(event -> showMainView(stage));  // Add click event to the label

        // Use a StackPane to overlay the label on the image
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, startLabel);
        StackPane.setAlignment(startLabel, Pos.BOTTOM_CENTER);

        // Set up the scene and stage
        Scene scene = new Scene(stackPane, 600, 400);
        stage.setTitle("Drink Ordering System");
        stage.setScene(scene);
        stage.show();
    }

    private void showMainView(Stage stage) {
        MainView mainView = new MainView();
        Scene mainScene = new Scene(mainView.getMainLayout(), 600, 400);

        // Set up buttons to switch views
        mainView.getMenuButton().setOnAction(e -> showMenuView(stage));
        mainView.getOrderButton().setOnAction(e -> showOrderView(stage));
        mainView.getCustomerButton().setOnAction(e -> showCustomerView(stage));

        stage.setScene(mainScene);
    }

    private void showMenuView(Stage stage) {
        menuView = new MenuView(stage, stage.getScene(), orderController); // Pass orderController to MenuView
        Scene menuScene = new Scene(menuView.getMenuLayout(), 600, 400);
        stage.setScene(menuScene);
    }

    private void showOrderView(Stage stage) {
        if (menuView != null) {
            OrderView orderView = new OrderView(orderController);
            Scene orderScene = new Scene(orderView.getOrderLayout(), 600, 400);
            stage.setScene(orderScene);
        } else {
            // Provide feedback if the menuView is not initialized
            System.out.println("Please select items from the menu first.");
        }
    }

    private void showCustomerView(Stage stage) {
        Scene mainScene = stage.getScene(); // Store the current main scene
        CustomerView customerView = new CustomerView(stage, mainScene); // Pass the stage and mainScene to CustomerView
        Scene customerScene = new Scene(customerView.getCustomerLayout(), 600, 400);
        stage.setScene(customerScene);
    }


    public static void main(String[] args) {
        launch();
    }
}
