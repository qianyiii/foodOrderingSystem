module com.qianyi.foodorderingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.qianyi.foodorderingsystem to javafx.fxml;
    exports com.qianyi.foodorderingsystem;
}