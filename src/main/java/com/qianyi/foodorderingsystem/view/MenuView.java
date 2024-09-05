package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.model.Customer;
import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Order;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuView {

    private BorderPane menuLayout;
    private List<String> orderedItems;
    private OrderController orderController;
    private Order currentOrder;
    private OrderView orderView;

    public MenuView(Stage primaryStage, Scene mainScene, OrderController orderController) {
        this.orderController = orderController;
        this.orderView = new OrderView(orderController);

        menuLayout = new BorderPane();
        orderedItems = new ArrayList<>();

        Customer customer = new Customer(1, "qy", "01234567", "qy@email.com");
        orderController.createNewOrder(customer);

        menuLayout.setTop(createTopBar(primaryStage, mainScene));
        menuLayout.setLeft(createLeftBar());
        menuLayout.setCenter(displayMenu());
        menuLayout.setRight(orderView.getOrderLayout());
    }

    public BorderPane getMenuLayout() {
        return menuLayout;
    }

    public List<String> getOrderedItems() {
        return orderedItems;
    }

    private MenuBar createTopBar(Stage primaryStage, Scene mainScene) {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #3d3d3d; -fx-color: #000000");

        Menu menuMenu = new Menu("Options");
        MenuItem backMenuItem = new MenuItem("Back");

        backMenuItem.setOnAction(e -> primaryStage.setScene(mainScene));
        menuMenu.getItems().add(backMenuItem);

        menuBar.getMenus().addAll(menuMenu);

        return menuBar;
    }

    private VBox createLeftBar() {
        VBox vb = new VBox();

        MenuButton itemDrink1 = new MenuButton("Coffee");
        itemDrink1.setPrefWidth(150);
        MenuButton itemDrink2 = new MenuButton("Juice");
        itemDrink2.setPrefWidth(150);
        MenuButton itemDrink3 = new MenuButton("Tea");
        itemDrink3.setPrefWidth(150);

        vb.getChildren().addAll(itemDrink1, itemDrink2, itemDrink3);

        return vb;
    }

    private GridPane displayMenu() {
        GridPane grid = new GridPane();

        int col = 0;
        int row = 0;
        int rowLabel = 1;
        String[] drinkNames = {"Cappuccino", "Espresso", "Latte", "Mocha"};
        double[] prices = {5.99, 3.99, 4.99, 6.65};
        Button btnOrder;
        Font font = Font.font("Verdana", FontWeight.BOLD, 15);

        for (int i = 0; i < drinkNames.length; i++) {

            String imagePath = "/com/qianyi/foodorderingsystem/coffee" + (i + 1) + ".png";
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

            grid.add(imageView, col, row, 1, 1);

            HBox hb = new HBox();
            hb.setSpacing(60);
            hb.setPadding(new Insets(10));
            Text txtPrice = new Text("From RM" + prices[i]);
            txtPrice.setFont(font);
            txtPrice.setFill(Color.GREEN);
            btnOrder = new Button("Add to Order");

            final String itemName = drinkNames[i];
            final double price = prices[i];
            btnOrder.setOnAction(e -> {
                // Create Drink object
                Drink drink = new Drink(1, itemName, price, "Regular", "Coffee");

                // Use orderController to add drink to the current order
                orderController.addDrinkToOrder(drink);

                // Update OrderView
                orderView.addDrinkToOrder(drink);

                showSuccessMessage("Successfully added " + itemName + " to order!");
            });

            hb.getChildren().addAll(txtPrice, btnOrder);

            grid.add(hb, col, rowLabel, 1, 1);

            if (col < 1) {
                col++;
            } else {
                row += 2;
                rowLabel += 2;
                col = 0;
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
