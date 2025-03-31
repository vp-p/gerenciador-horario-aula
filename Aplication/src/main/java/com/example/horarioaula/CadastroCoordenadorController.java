package com.example.horarioaula;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroCoordenadorController {

    public EventHandler<ActionEvent> PopupCoordenador(){
        return event ->{

            Stage popup = new Stage();
            popup.setTitle("Adicionar Coordenador");

            VBox popupLayout = new VBox(10);
            popupLayout.getStyleClass().add("popup-container");

            Label labelNome = new Label("Nome do Coordenador");
            labelNome.getStyleClass().add("popup-label");
            TextField textFieldNome = new TextField();
            textFieldNome.setPromptText("Nome");
            textFieldNome.getStyleClass().add("popup-textfield");

            Label labelEmail = new Label("Email do Coordenador");
            labelEmail.getStyleClass().add("popup-label");
            TextField textFieldEmail = new TextField();
            textFieldEmail.setPromptText("Email");
            textFieldEmail.getStyleClass().add("popup-textfield");

            Label labelSenha = new Label("Senha do Coordenador");
            labelSenha.getStyleClass().add("popup-label");
            TextField textFieldSenha = new TextField();
            textFieldSenha.setPromptText("Senha");
            textFieldSenha.getStyleClass().add("popup-textfield");

            Label labelCurso = new Label("Curso do Coordenador:");
            labelCurso.getStyleClass().add("popup-label");
            ComboBox comboBoxCurso = new ComboBox();
            comboBoxCurso.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
            comboBoxCurso.setPromptText("Selecione o curso");

            comboBoxCurso.getItems().addAll("Banco de Dados", "Análise e Desenvolvimento de Sistemas", "Desenvolvimento de Software Multiplataforma",
                    "Gestão Da Produção Industrial", "Logística", "Manufatura Avançada", "Manutenção de Aeronaves", "Projetos de Estruturas Aeronáuticas");


            Button cadastroButton = new Button("Cadastrar");
            cadastroButton.getStyleClass().add("popup-button");


            HBox buttons = new HBox(10, cadastroButton);
            buttons.getStyleClass().add("popup-button-container");
            buttons.setAlignment(Pos.CENTER);

            popupLayout.getChildren().addAll(labelNome, textFieldNome);
            popupLayout.getChildren().addAll(labelEmail, textFieldEmail);
            popupLayout.getChildren().addAll(labelSenha, textFieldSenha, labelCurso,comboBoxCurso,buttons);

            Scene popupScene = new Scene(popupLayout, 510, 510);
            popupScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            popup.setScene(popupScene);
            popup.show();
        };
    }
}
