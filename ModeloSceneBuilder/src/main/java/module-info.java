module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
}
