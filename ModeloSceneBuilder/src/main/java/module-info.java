module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires io.github.cdimascio.dotenv.java;
    requires java.desktop;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    opens org.example.classes to javafx.base;

}
