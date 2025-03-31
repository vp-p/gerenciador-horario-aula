package com.example.horarioaula;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class RecuperarSenha extends Application {
    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));


        ImageView logo = new ImageView(new Image(getClass().getResource("/imgs/Sinapse.png").toString()));
        logo.setFitWidth(124);
        logo.setFitHeight(35);


        Label label = new Label("Digite seu e-mail:");
        TextField emailField = new TextField();

        emailField.setMaxWidth(250);
        emailField.setPrefHeight(30);
        emailField.setPromptText("exemplo@email.com");


        Button sendButton = new Button("Enviar");
        sendButton.setOnAction(e -> System.out.println("E-mail digitado: " + emailField.getText()));


        vbox.getChildren().addAll(logo, label, emailField, sendButton);


        Scene scene = new Scene(vbox, 1200, 700);
        stage.setTitle("Entrada de E-mail");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
