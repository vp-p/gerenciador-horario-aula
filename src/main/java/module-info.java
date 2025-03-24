module com.example.horarioaula {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.horarioaula to javafx.fxml;
    exports com.example.horarioaula;
}