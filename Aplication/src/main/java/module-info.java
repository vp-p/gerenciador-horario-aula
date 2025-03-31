module com.example.horarioaula {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.horarioaula to javafx.fxml;
    exports com.example.horarioaula;
}