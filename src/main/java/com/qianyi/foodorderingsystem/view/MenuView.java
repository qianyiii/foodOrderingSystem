package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuView {

    private BorderPane menuLayout;
    private OrderController orderController;
    private OrderView orderView;
    private GridPane gridPane;
    private Map<String, Drink[]> categoryDrinksMap;

    public MenuView(Stage primaryStage, Scene mainScene, OrderController orderController) {
        this.orderController = orderController;
        this.orderView = new OrderView(orderController);
        this.gridPane = new GridPane();
        this.categoryDrinksMap = new HashMap<>();

        // Sample data for drinks categorized
        categoryDrinksMap.put("Coffee", new Drink[]{
                new Drink(1, "Cappuccino", 5.99, "Regular", "Coffee"),
                new Drink(2, "Espresso", 3.99, "Regular", "Coffee"),
                new Drink(3, "Latte", 4.99, "Regular", "Coffee"),
                new Drink(4, "Mocha", 6.65, "Regular", "Coffee")
        });
        categoryDrinksMap.put("Juice", new Drink[]{
                new Drink(5, "Orange Juice", 4.50, "Regular", "Juice"),
                new Drink(6, "Grapefruit Juice", 4.00, "Regular", "Juice"),
                new Drink(7, "Watermelon Juice", 4.00, "Regular", "Juice"),
                new Drink(8, "Kiwi Juice", 4.00, "Regular", "Juice")
        });
        categoryDrinksMap.put("Tea", new Drink[]{
                new Drink(9, "Black Tea", 3.00, "Regular", "Tea"),
                new Drink(10, "Jasmine Tea", 3.50, "Regular", "Tea"),
                new Drink(11, "Lemon Tea", 3.50, "Regular", "Tea"),
                new Drink(12, "Oolong Tea", 3.50, "Regular", "Tea")
        });

        // Create a new order for a customer
        Customer customer = new Customer(1, "qy", "01234567", "qy@email.com");
        orderController.createNewOrder(customer);

        menuLayout = new BorderPane();
        menuLayout.setPadding(new Insets(20));


        // Set background image
        setBackground("/com/qianyi/foodorderingsystem/Background2.png");

        menuLayout.setTop(createTopBar(primaryStage, mainScene));
        menuLayout.setLeft(createLeftBar());
        menuLayout.setCenter(displayMenu("Coffee")); // Default category
        menuLayout.setRight(orderView.getOrderLayout());
    }

    public BorderPane getMenuLayout() {
        return menuLayout;
    }

    private HBox createTopBar(Stage primaryStage, Scene mainScene) {
        // Create the "Back" label
        Label backLabel = new Label("<- Back");
        backLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");
        backLabel.setOnMouseClicked(e -> primaryStage.setScene(mainScene)); // Go back to the main view on click

        // Position the "Back" button at the top-left corner
        HBox topBar = new HBox(backLabel);
        topBar.setAlignment(Pos.TOP_LEFT);
        menuLayout.setTop(topBar);

        return topBar;
    }

    // Corrected Left Bar with category buttons
    private VBox createLeftBar() {
        VBox vb = new VBox();
        vb.setSpacing(20); // Add spacing between buttons
        vb.setPadding(new Insets(10));

        // Store the background image path once
        String buttonBackgroundImage = getClass().getResource("/com/qianyi/foodorderingsystem/buttonBackground.png").toExternalForm();

        // Button for Coffee category
        Button itemDrink1 = new Button();
        itemDrink1.setPrefWidth(150);
        itemDrink1.setPrefHeight(30);
        itemDrink1.setText("Coffee");

        // Apply the same background image style
        itemDrink1.setStyle("-fx-background-image: url('" + buttonBackgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold;");
        itemDrink1.setOnAction(e -> menuLayout.setCenter(displayMenu("Coffee")));

        // Button for Juice category
        Button itemDrink2 = new Button();
        itemDrink2.setPrefWidth(150);
        itemDrink2.setPrefHeight(30);
        itemDrink2.setText("Juice");

        // Reuse the same background image style
        itemDrink2.setStyle("-fx-background-image: url('" + buttonBackgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold;");
        itemDrink2.setOnAction(e -> menuLayout.setCenter(displayMenu("Juice")));

        // Button for Tea category
        Button itemDrink3 = new Button();
        itemDrink3.setPrefWidth(150);
        itemDrink3.setPrefHeight(30);
        itemDrink3.setText("Tea");
        // Reuse the same background image style
        itemDrink3.setStyle("-fx-background-image: url('" + buttonBackgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-text-fill: black; " +
                "-fx-font-weight: bold;");
        itemDrink3.setOnAction(e -> menuLayout.setCenter(displayMenu("Tea")));

        // Add buttons to the VBox
        vb.getChildren().addAll(itemDrink1, itemDrink2, itemDrink3);

        return vb;
    }

    // Method to set background image for the menu layout
    private void setBackground(String imagePath) {
        Image backgroundImage = new Image(getClass().getResource(imagePath).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));

        menuLayout.setBackground(new Background(bgImage));
    }

    // Display the selected category's drinks
    private GridPane displayMenu(String category) {
        GridPane grid = new GridPane();
        grid.setHgap(20); // Horizontal spacing between grid elements
        grid.setVgap(20); // Vertical spacing between grid elements

        int col = 0;
        int row = 0;
        Drink[] drinks = categoryDrinksMap.get(category);

        if (drinks == null) {
            System.out.println("No drinks available for category: " + category);
            return grid; // No drinks for this category
        }

        Font font = Font.font("Verdana", FontWeight.BOLD, 15);

        for (int i = 0; i < drinks.length; i++) {
            Drink drink = drinks[i];
            String imagePath = "/com/qianyi/foodorderingsystem/" + category.toLowerCase() + (i + 1) + ".png";
            URL resourceUrl = getClass().getResource(imagePath);

            if (resourceUrl == null) {
                System.out.println("Resource not found: " + imagePath);
                continue;
            }

            Image image = new Image(resourceUrl.toExternalForm());
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(200);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);

            // Add the image to the grid
            grid.add(imageView, col, row);

            HBox hb = new HBox();
            hb.setSpacing(20);
            hb.setPadding(new Insets(10));
            Text txtPrice = new Text("From RM" + drink.getPrice());
            txtPrice.setFont(font);
            txtPrice.setFill(Color.GREEN);
            Button btnOrder = new Button("Add to Order");

            btnOrder.setOnAction(e -> {
                orderController.addDrinkToOrder(drink);
                orderView.addDrinkToOrder(drink);
                showSuccessMessage("Successfully added " + drink.getName() + " to order!");
            });

            hb.getChildren().addAll(txtPrice, btnOrder);
            grid.add(hb, col, row + 1);

            if (col < 1) {
                col++;
            } else {
                col = 0;
                row += 2;
            }
        }

        return grid;
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
