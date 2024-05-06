module com.example.cocheconproperties {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.cocheconproperties to javafx.fxml;
    exports com.example.cocheconproperties;
}