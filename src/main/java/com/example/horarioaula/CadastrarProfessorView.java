package com.example.horarioaula;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class CadastrarProfessorView extends Application {
    private TableView<CadastrarProfessor> tabela;
    private ObservableList<CadastrarProfessor> dados;

    @Override
    public void start(Stage stage) {
        // Painel principal (BorderPane)
        BorderPane borderPane = new BorderPane();

        // Menu lateral (fora da área de conteúdo principal)
        VBox sideMenu = new VBox(20);
        sideMenu.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, -4, 4);");

        // Logo do menu lateral
        ImageView logo = new ImageView(new Image(getClass().getResource("/imgs/Sinapse.png").toString()));
        logo.setFitWidth(124);
        logo.setFitHeight(35);
        sideMenu.getChildren().add(logo);

        // Criar ícones para o menu lateral
        ImageView icon1 = new ImageView(new Image(getClass().getResource("/imgs/Calendar.png").toString()));
        ImageView icon2 = new ImageView(new Image(getClass().getResource("/imgs/User.png").toString()));

        // Ajustar o tamanho dos ícones
        for (ImageView icon : new ImageView[]{icon1, icon2}) {
            icon.setFitWidth(20);
            icon.setFitHeight(20);
        }

        // Adicionando os itens ao menu lateral
        Label separator1 = new Label("Home");
        separator1.setStyle("-fx-font-size: 18px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn1 = new Button("Grade de aula", icon1);

        Label separator2 = new Label("Funcionários");
        separator2.setStyle("-fx-font-size: 18px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn2 = new Button("Professores", icon2);

        // Adiciona os botões ao menu lateral
        sideMenu.getChildren().addAll(separator1, btn1, separator2, btn2);

        // Menu superior dentro da área de conteúdo
        HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.CENTER_RIGHT);
        topMenu.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px;");
        HBox.setMargin(topMenu, new Insets(0, 0, 20, 0));

        ImageView profileImage = new ImageView(new Image(getClass().getResource("/imgs/perfil.png").toString()));
        profileImage.setFitWidth(40);
        profileImage.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        profileImage.setClip(clip);
        topMenu.getChildren().add(profileImage);

        // Área de conteúdo principal (contendo o formulário de cadastro de professores e a tabela)
        VBox contentArea = new VBox(10);
        contentArea.setPadding(new Insets(20));

        // Formulário de cadastro
        HBox form = new HBox(10);
        form.setAlignment(Pos.CENTER);

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        Button btnAdicionar = new Button("Adicionar");

        form.getChildren().addAll(nomeField, emailField, btnAdicionar);

        // Criar a tabela de professores
        tabela = new TableView<>();

        // Colunas da tabela
        TableColumn<CadastrarProfessor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<CadastrarProfessor, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tabela.getColumns().addAll(colNome, colEmail);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        dados = FXCollections.observableArrayList();
        tabela.setItems(dados);

        // Ação do botão "Adicionar"
        btnAdicionar.setOnAction(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            if (!nome.isEmpty() && !email.isEmpty()) {
                dados.add(new CadastrarProfessor(nome, email));
                nomeField.clear();
                emailField.clear();
            }
        });

        // Adiciona o menu superior e a tabela ao conteúdo principal
        contentArea.getChildren().add(topMenu);
        contentArea.getChildren().add(form);
        contentArea.getChildren().add(tabela);

        // Definir o conteúdo central
        borderPane.setCenter(contentArea);

        // Menu lateral no lado esquerdo
        borderPane.setLeft(sideMenu);

        BorderPane.setMargin(sideMenu, new Insets(0, 20, 0, 0));

        // Criar a cena
        Scene scene = new Scene(borderPane, 1200, 800);
        stage.setTitle("Cadastro de Professores");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
