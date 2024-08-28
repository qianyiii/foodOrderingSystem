package com.qianyi.foodorderingsystem.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
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

public class MenuView {

    private BorderPane menuLayout;

    public MenuView(Stage primaryStage, Scene mainScene) {
        // Initialize the main layout
        menuLayout = new BorderPane();

        // Set top, left, and center sections
        menuLayout.setTop(createTopBar(primaryStage, mainScene));
        menuLayout.setLeft(createLeftBar());
        menuLayout.setCenter(displayMenu());
    }

    public BorderPane getMenuLayout() {
        return menuLayout;
    }

    private MenuBar createTopBar(Stage primaryStage, Scene mainScene) {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #3d3d3d; -fx-color: #000000");

        // Create a "Back" menu with a MenuItem
        Menu menuMenu = new Menu("Options");
        MenuItem backMenuItem = new MenuItem("Back");

        // Set action to switch back to the MainView
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
        double[] price = {5.99, 3.99, 4.99, 6.65};
        Button btnOrder;
        Font font = Font.font("Verdana", FontWeight.BOLD, 15);

        for (int i = 1; i <= 5; i++) {

            String imagePath = "/com/qianyi/foodorderingsystem/coffee" + i + ".png";
            URL resourceUrl = getClass().getResource(imagePath);

            if (resourceUrl == null) {
                System.out.println("Resource not found: " + imagePath);
                continue;  // Skip this iteration if the resource is not found
            }

            Image image = new Image(resourceUrl.toExternalForm());
            ImageView imageView = new ImageView(image);

            // 设置图像大小
            imageView.setFitWidth(200);  // 设置固定宽度
            imageView.setFitHeight(150);  // 设置固定高度
            imageView.setPreserveRatio(true);  // 保持宽高比

            grid.add(imageView, col, row, 1, 1);

            HBox hb = new HBox();
            hb.setSpacing(60);
            hb.setPadding(new Insets(10));
            Text txtPrice = new Text("From RM" + price[i - 1]);
            txtPrice.setFont(font);
            txtPrice.setFill(Color.GREEN);
            btnOrder = new Button("Add to Order");
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
}
