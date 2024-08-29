package com.qianyi.foodorderingsystem.view;

import com.qianyi.foodorderingsystem.controller.OrderController;
import com.qianyi.foodorderingsystem.model.Order;
import com.qianyi.foodorderingsystem.model.Drink;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OrderView {
    private VBox orderLayout;
    private OrderController orderController;
    private Order order;
    private ListView<String> orderListView;
    private Text totalPriceText;

    public OrderView(OrderController orderController) {
        this.orderController = orderController;
        this.order = orderController.createOrder();

        // Initialize the layout and set padding and spacing
        orderLayout = new VBox();
        orderLayout.setSpacing(10);
        orderLayout.setPadding(new Insets(10));

        Text header = new Text("Your Order:");
        orderLayout.getChildren().add(header);

        // Create a ListView to display ordered items
        orderListView = new ListView<>();
        orderLayout.getChildren().add(orderListView);

        // Display the total price of the order
        totalPriceText = new Text("Total: RM" + orderController.getTotalPrice(order));
        orderLayout.getChildren().add(totalPriceText);

        // Initially update the order view
        updateOrderView();
    }

    public void addDrinkToOrder(Drink drink) {
        orderController.addDrinkToOrder(order, drink);
        updateOrderView();
    }

    public void removeDrinkFromOrder(Drink drink) {
        orderController.removeDrinkFromOrder(order, drink);
        updateOrderView();
    }

    private void updateOrderView() {
        // Clear the ListView
        orderListView.getItems().clear();

        // Add each drink to the ListView
        for (Drink drink : order.getDrinks()) {
            orderListView.getItems().add(drink.getName() + " - RM" + drink.getPrice());
        }

        // Update the total price
        totalPriceText.setText("Total: RM" + orderController.getTotalPrice(order));
    }

    public VBox getOrderLayout() {
        return orderLayout;
    }
}
