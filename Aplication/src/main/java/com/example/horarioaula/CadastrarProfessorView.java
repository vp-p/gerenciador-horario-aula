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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CadastrarProfessorView extends Application {
    private TableView<CadastrarProfessor> tabela;
    private ObservableList<CadastrarProfessor> dados;

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        // Menu lateral
        VBox sideMenu = criarMenuLateral();
        borderPane.setLeft(sideMenu);

        // Menu superior
        HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.CENTER_RIGHT);
        topMenu.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px;");

        ImageView profileImage = new ImageView(new Image(getClass().getResource("/imgs/perfil.png").toString()));
        profileImage.setFitWidth(40);
        profileImage.setFitHeight(40);
        profileImage.setClip(new Circle(20, 20, 20));
        topMenu.getChildren().add(profileImage);

        // Área de conteúdo principal
        VBox contentArea = new VBox();
        contentArea.setSpacing(10);
        contentArea.setPadding(new Insets(20, 10, 10, 10));

        // Header com título e botões de ação
        VBox headerBox = criarHeader();
        contentArea.getChildren().addAll(topMenu, headerBox);

        // Criar a tabela de professores
        tabela = new TableView<>();
        configurarTabela();

        // Adiciona a tabela ao conteúdo principal
        contentArea.getChildren().add(tabela);
        borderPane.setCenter(contentArea);
        BorderPane.setMargin(sideMenu, new Insets(0, 20, 0, 0));

        // Criar a cena
        Scene scene = new Scene(borderPane, 1200, 800);
        stage.setTitle("Cadastro de Professores");
        stage.setScene(scene);
        stage.show();

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());



    }

    private VBox criarMenuLateral() {
        VBox sideMenu = new VBox(10);
        sideMenu.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, -4, 4);");

        ImageView logo = new ImageView(new Image(getClass().getResource("/imgs/Sinapse.png").toString()));
        logo.setFitWidth(124);
        logo.setFitHeight(35);
        sideMenu.getChildren().add(logo);

        ImageView[] icons = {
                new ImageView(new Image(getClass().getResource("/imgs/Calendar.png").toString())),
                new ImageView(new Image(getClass().getResource("/imgs/User.png").toString())),
                new ImageView(new Image(getClass().getResource("/imgs/FolderUser.png").toString())),
                new ImageView(new Image(getClass().getResource("/imgs/red.png").toString())),
                new ImageView(new Image(getClass().getResource("/imgs/blue.png").toString())),
                new ImageView(new Image(getClass().getResource("/imgs/purple.png").toString()))
        };

        for (ImageView icon : icons) {
            icon.setFitWidth(20);
            icon.setFitHeight(20);
        }

        Label separator1 = new Label("Home");
        separator1.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn1 = new Button("Grade de aula", icons[0]);

        Label separator2 = new Label("Funcionários");
        separator2.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn2 = new Button("Professores", icons[1]);
        Button btn3 = new Button("Coordenadores", icons[2]);

        Label separator3 = new Label("Geral");
        separator3.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn4 = new Button("Cursos", icons[3]);
        Button btn5 = new Button("Semestres", icons[4]);
        Button btn6 = new Button("Disciplinas", icons[5]);

        sideMenu.getChildren().addAll(separator1, btn1, separator2, btn2, btn3, separator3, btn4, btn5, btn6);
        return sideMenu;
    }

    private VBox criarHeader() {
        VBox headerBox = new VBox(5);
        headerBox.setPadding(new Insets(20, 24, 20, 24));
        headerBox.getStyleClass().add("header-box");

        Label titulo = new Label("Professores");
        titulo.getStyleClass().add("titulo");
        Label subtitulo = new Label("Gerencie e consulte os professores cadastrados.");
        subtitulo.getStyleClass().add("subtitulo");

        HBox actionButtons = criarBotoesAcoes();
        VBox headerText = new VBox(5);
        headerText.setAlignment(Pos.CENTER_LEFT);
        headerText.getChildren().addAll(titulo, subtitulo);

        HBox topContent = new HBox(20);
        topContent.setAlignment(Pos.CENTER_LEFT);
        topContent.getChildren().addAll(headerText, actionButtons);

        headerBox.getChildren().add(topContent);
        return headerBox;
    }

    private HBox criarBotoesAcoes() {
        HBox actionButtons = new HBox(10);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);

        ImageView deletarIcon = new ImageView(new Image(getClass().getResource("/imgs/trash.png").toString()));
        ImageView filtrosIcon = new ImageView(new Image(getClass().getResource("/imgs/filtros.png").toString()));
        ImageView exportarIcon = new ImageView(new Image(getClass().getResource("/imgs/export.png").toString()));
        ImageView addIcon = new ImageView(new Image(getClass().getResource("/imgs/plus.png").toString()));

        for (ImageView icon : new ImageView[]{deletarIcon, filtrosIcon, exportarIcon, addIcon}) {
            icon.setFitWidth(20);
            icon.setFitHeight(20);
        }

        Button deletar = new Button("Deletar", deletarIcon);
        Button filtros = new Button("Filtros", filtrosIcon);
        Button exportar = new Button("Exportar", exportarIcon);
        Button adicionar = new Button("Adicionar professor", addIcon);
        adicionar.setStyle("-fx-background-color: #1D4ED8; -fx-text-fill: white; -fx-pref-width: 200px;");
        adicionar.setOnAction(e -> abrirPopupCadastro());

        actionButtons.getChildren().addAll(deletar, filtros, exportar, adicionar);
        return actionButtons;
    }

    private void configurarTabela() {
        TableColumn<CadastrarProfessor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<CadastrarProfessor, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<CadastrarProfessor, String> colCurso = new TableColumn<>("Curso");
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));

        tabela.getColumns().addAll(colNome, colEmail, colCurso);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        dados = FXCollections.observableArrayList();
        tabela.setItems(dados);
    }

    private void abrirPopupCadastro() {
        Stage popup = new Stage();
        popup.setTitle("Adicionar Professor");

        VBox popupLayout = new VBox(10);
        popupLayout.getStyleClass().add("popup-container");

        Label label = new Label("Nome do Professor");
        TextField textField = new TextField();

        Button cadastrar = new Button("Cadastrar Professor");
        popupLayout.getChildren().addAll(label, textField, cadastrar);
        cadastrar.getStyleClass().add("popup-button");

        Scene popupScene = new Scene(popupLayout, 300, 200);
        popupScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        popup.setScene(popupScene);
        popup.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
