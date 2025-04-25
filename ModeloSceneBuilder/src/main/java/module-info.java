module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    opens org.example.dao to javafx.fxml;
}
