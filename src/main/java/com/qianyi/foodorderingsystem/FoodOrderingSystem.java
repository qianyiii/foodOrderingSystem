package com.qianyi.foodorderingsystem;

import com.qianyi.foodorderingsystem.view.CustomerView;
import com.qianyi.foodorderingsystem.view.MainView;
import com.qianyi.foodorderingsystem.view.MenuView;
import com.qianyi.foodorderingsystem.view.OrderView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FoodOrderingSystem extends Application {

    @Override
    public void start(Stage stage) {
        // Create MainView
        MainView mainView = new MainView();

        // Set up buttons to switch views
        Button menuButton = new Button("View Menu");
        Button orderButton = new Button("Place Order");
        Button customerButton = new Button("Customer Information");

        menuButton.setOnAction(e -> showMenuView(stage));
        orderButton.setOnAction(e -> showOrderView(stage));
        customerButton.setOnAction(e -> showCustomerView(stage));

        // Add buttons to main view
        mainView.getMainLayout().getChildren().addAll(menuButton, orderButton, customerButton);

        // Set up the main scene
        Scene mainScene = new Scene(mainView.getMainLayout(), 600, 400);
        stage.setTitle("Fauget Fresh Drink");
        stage.setScene(mainScene);
        stage.show();
    }

    private void showMenuView(Stage stage) {
        MenuView menuView = new MenuView();
        Scene menuScene = new Scene(menuView.getMenuLayout(), 600, 400);
        stage.setScene(menuScene);
    }

    private void showOrderView(Stage stage) {
        OrderView orderView = new OrderView();
        Scene orderScene = new Scene(orderView.getOrderLayout(), 600, 400);
        stage.setScene(orderScene);
    }

    private void showCustomerView(Stage stage) {
        CustomerView customerView = new CustomerView();
        Scene customerScene = new Scene(customerView.getCustomerLayout(), 600, 400);
        stage.setScene(customerScene);
    }

    public static void main(String[] args) {
        launch();
    }
}
