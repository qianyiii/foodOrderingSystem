package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class MenuView {

    private VBox menuLayout;
    private ListView<String> menuListView;

    public MenuView() {
        // Initialize the layout
        menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);

        // Add title
        Label menuLabel = new Label("Menu");
        menuLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // List to display menu items
        menuListView = new ListView<>();

        // Button to add selected item to order
        Button addToOrderButton = new Button("Add to Order");

        // Add components to the layout
        menuLayout.getChildren().addAll(menuLabel, menuListView, addToOrderButton);
    }

    public VBox getMenuLayout() {
        return menuLayout;
    }

    public ListView<String> getMenuListView() {
        return menuListView;
    }
}
