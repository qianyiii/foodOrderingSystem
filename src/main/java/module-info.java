module com.qianyi.foodorderingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.qianyi.foodorderingsystem to javafx.fxml;
    exports com.qianyi.foodorderingsystem;
}