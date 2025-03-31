package com.example.horarioaula;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TelaLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sinapse - Login");
        primaryStage.setResizable(true); // Deixa o usuario redimensionar a tela

        // Layout principal
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        // Parte do email e senha
        VBox colunaEquerda = new VBox(20);
        colunaEquerda.setPadding(new Insets(50));
        colunaEquerda.setAlignment(Pos.TOP_CENTER);

        // Logo
        Label logo = new Label("Sinapse");
        logo.setStyle(
                "-fx-text-fill: #0070FF; " + // Cor azul
                        "-fx-font-size: 36px; " +     // Tamanho grande
                        "-fx-font-weight: bold;"      // Negrito
        );
        colunaEquerda.getChildren().add(logo);

        // Campo E-mail
        Label emailLabel = new Label("E-mail");
        TextField emailField = new TextField();
        emailField.setPromptText("Digite seu e-mail");
        emailField.setMaxWidth(375);
        emailField.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

        Separator emailSeparator = new Separator();
        emailSeparator.setPrefWidth(375);

        colunaEquerda.getChildren().addAll(emailLabel, emailField, emailSeparator);

        // Campo Senha
        Label senhaLabel = new Label("Senha");

        // ocultar e mostrar senha
        TextField senhaVisibleField = new TextField(); // Para mostrar a senha
        PasswordField senhaHiddenField = new PasswordField(); // Para ocultar a senha

        senhaVisibleField.setPromptText("Digite sua senha");
        senhaHiddenField.setPromptText("Digite sua senha");

        // Tamanho da largura da senha
        senhaVisibleField.setMaxWidth(375);
        senhaHiddenField.setMaxWidth(375);

        senhaVisibleField.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
        senhaHiddenField.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");

        // controla a visibilidade da senha
        BooleanProperty showPassword = new SimpleBooleanProperty(false);

        // Alternar entre TextField e PasswordField
        StackPane passwordContainer = new StackPane();
        passwordContainer.getChildren().add(senhaHiddenField); // Inicialmente oculto

        showPassword.addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                passwordContainer.getChildren().clear();
                passwordContainer.getChildren().add(senhaVisibleField);
                senhaVisibleField.setText(senhaHiddenField.getText()); // Copia o texto para o TextField
            } else {
                passwordContainer.getChildren().clear();
                passwordContainer.getChildren().add(senhaHiddenField);
                senhaHiddenField.setText(senhaVisibleField.getText()); // Copia o texto para o PasswordField
            }
        });

        // Ícone de cadeado (Esta em Unicode)
        Label eyeIcon = new Label("\uD83D\uDD12"); // Cadeado fechado (senha oculta inicialmente)
        eyeIcon.setStyle("-fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 0 8px;");

        // Ação ao clicar no ícone para mudar o icone do cadeado
        eyeIcon.setOnMouseClicked(event -> {
            showPassword.set(!showPassword.get());
            if (showPassword.get()) {
                eyeIcon.setText("\uD83D\uDD13"); // Alterna para cadeado aberto (senha visível)
            } else {
                eyeIcon.setText("\uD83D\uDD12"); // Alterna para cadeado fechado (senha oculta)
            }
        });

        // Container para posicionar o ícone à direita do campo
        StackPane passwordWrapper = new StackPane(passwordContainer, eyeIcon);
        StackPane.setAlignment(eyeIcon, Pos.CENTER_RIGHT);
        StackPane.setMargin(eyeIcon, new Insets(0, 10, 0, 0));

        // Linha do campo de senha
        Separator senhaSeparator = new Separator();
        senhaSeparator.setPrefWidth(375);

        colunaEquerda.getChildren().addAll(senhaLabel, passwordWrapper, senhaSeparator);

        // Botão Entrar
        Button entrarButton = new Button("Entrar");
        entrarButton.setStyle(
                "-fx-background-color: #3B8FEE; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold;"
        );
        entrarButton.setPrefWidth(375);
        entrarButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Login efetuado com sucesso!");
            alert.showAndWait();
        });
        colunaEquerda.getChildren().add(entrarButton);

        // Botão "Esqueci minha senha"
        Button esqueciSenhaButton = new Button("Esqueci minha senha");
        esqueciSenhaButton.setStyle(
                "-fx-background-color: transparent; " + // Fundo transparente
                        "-fx-text-fill: #4A90E2; " +           // Cor azul
                        "-fx-underline: true; " +              // Sublinhado
                        "-fx-font-size: 14px;"                 // Tamanho da fonte
        );
        esqueciSenhaButton.setOnAction(e -> {
            // Abre a tela de redefinição de senha
            Stage redefinicaoSenhaStage = new Stage(); // Cria uma nova janela
            try {
                new RecuperarSenha().start(redefinicaoSenhaStage); // Inicializa a tela
            } catch (Exception ex) {
                ex.printStackTrace(); // Trata possíveis erros
            }
        });
        colunaEquerda.getChildren().add(esqueciSenhaButton);

        // Coluna direita (Quadrado azul)
        Region colunaDireita = new Region();
        colunaDireita.setStyle(
                "-fx-background-color: #3B8FEE; " +
                        "-fx-background-radius: 20;"
        );

        // Dimensões do quadrado azul
        colunaDireita.setPrefWidth(567);
        colunaDireita.setPrefHeight(565);
        HBox.setHgrow(colunaDireita, Priority.ALWAYS);

        // Monta o layout
        root.getChildren().addAll(colunaEquerda, colunaDireita);

        // Cria a cena e exibindo
        Scene scene = new Scene(root, 1200, 700); // Tamanho inicial da tela
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}