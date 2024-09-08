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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The main entry point of the Drink Ordering System application.
 * <p>
 * This class extends {@link Application} and initializes the application's primary user interface.
 * It sets up the initial scene with a welcome image and label, and handles transitions to different views of the application.
 * </p>
 */
public class DrinkOrderingSystem extends Application {

    private MenuView menuView;
    private OrderService orderService;
    private OrderController orderController;

    /**
     * Initializes and displays the primary stage of the application.
     * <p>
     * This method is called by the JavaFX runtime to start the application. It sets up the initial user interface,
     * including the welcome image and label, and binds their properties to the stage's dimensions.
     * </p>
     *
     * @param stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        // Initialize the OrderService and OrderController
        orderService = new OrderService();
        // Initialize the OrderController with the OrderService
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

        // Load custom font from resources
        Font customFont = Font.loadFont(getClass().getResourceAsStream("CaveatBold.ttf"), 24);


        // Create "Start to Order Now!" label
        Label startLabel = new Label("Start to Order Now!");
        startLabel.setFont(customFont); // Apply custom font
        startLabel.setStyle("-fx-text-fill: black;");
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

    /**
     * Displays the main view of the application.
     * <p>
     * This method sets up the main view scene, initializes the {@link MainView} with the primary stage,
     * and sets up event handlers for switching between different views.
     * </p>
     *
     * @param stage the primary stage for this application.
     */
    private void showMainView(Stage stage) {
        MainView mainView = new MainView(stage);
        Scene mainScene = new Scene(mainView.getMainLayout(), 600, 400);

        // Set up buttons to switch views
        mainView.getMenuButton().setOnAction(e -> showMenuView(stage));
        mainView.getCustomerButton().setOnAction(e -> showCustomerView(stage));

        stage.setScene(mainScene);
    }

    /**
     * Displays the menu view of the application.
     * <p>
     * This method initializes the {@link MenuView} with the primary stage and the {@link OrderController},
     * then sets up the scene to display the menu.
     * </p>
     *
     * @param stage the primary stage for this application.
     */
    private void showMenuView(Stage stage) {
        menuView = new MenuView(stage, stage.getScene(), orderController); // Pass orderController to MenuView
        Scene menuScene = new Scene(menuView.getMenuLayout(), 1000, 630);
        stage.setScene(menuScene);
    }

    /**
     * Displays the customer view of the application.
     * <p>
     * This method initializes the {@link CustomerView} with the primary stage and the current scene,
     * then sets up the scene to display the customer view.
     * </p>
     *
     * @param stage the primary stage for this application.
     */
    private void showCustomerView(Stage stage) {
        Scene mainScene = stage.getScene(); // Store the current main scene
        CustomerView customerView = new CustomerView(stage, mainScene); // Pass the stage and mainScene to CustomerView
        Scene customerScene = new Scene(customerView.getCustomerLayout(), 600, 400);
        stage.setScene(customerScene);
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
