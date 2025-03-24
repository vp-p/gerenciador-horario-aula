package com.example.horarioaula;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.Circle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        // Painel principal (BorderPane)
        BorderPane borderPane = new BorderPane();

        // Menu lateral (fora da área de conteúdo principal)
        VBox sideMenu = new VBox(10);
        sideMenu.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, -4, 4);");

        // Logo do menu
        ImageView logo = new ImageView(new Image(getClass().getResource("/imgs/Sinapse.png").toString()));
        logo.setFitWidth(124);
        logo.setFitHeight(35);
        sideMenu.getChildren().add(logo);

        // Criar ícones para o menu
        ImageView icon1 = new ImageView(new Image(getClass().getResource("/imgs/Calendar.png").toString()));
        ImageView icon2 = new ImageView(new Image(getClass().getResource("/imgs/User.png").toString()));
        ImageView icon3 = new ImageView(new Image(getClass().getResource("/imgs/FolderUser.png").toString()));
        ImageView icon4 = new ImageView(new Image(getClass().getResource("/imgs/red.png").toString()));
        ImageView icon5 = new ImageView(new Image(getClass().getResource("/imgs/blue.png").toString()));
        ImageView icon6 = new ImageView(new Image(getClass().getResource("/imgs/purple.png").toString()));

        // Ajustar o tamanho dos ícones
        for (ImageView icon : new ImageView[]{icon1, icon2, icon3, icon4, icon5, icon6}) {
            icon.setFitWidth(20);
            icon.setFitHeight(20);
        }

        // Adicionando os itens ao menu lateral
        Label separator1 = new Label("Home");
        separator1.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn1 = new Button("Grade de aula", icon1);

        Label separator2 = new Label("Funcionários");
        separator2.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn2 = new Button("Professores", icon2);
        Button btn3 = new Button("Coordenadores", icon3);

        Label separator3 = new Label("Geral");
        separator3.setStyle("-fx-font-size: 14px; -fx-font-weight: normal; -fx-text-fill: #83899F;");
        Button btn4 = new Button("Cursos", icon4);
        Button btn5 = new Button("Semestres", icon5);
        Button btn6 = new Button("Disciplinas", icon6);

        // Adiciona os botões ao menu lateral
        sideMenu.getChildren().addAll(separator1, btn1, separator2, btn2, btn3, separator3, btn4, btn5, btn6);

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

        // Área de conteúdo principal (contendo o menu superior e a tabela)
        VBox contentArea = new VBox();
        contentArea.setSpacing(10);
        contentArea.setPadding(new Insets(20, 10, 10, 10));

        // Adiciona o menu superior e a tabela
        contentArea.getChildren().add(topMenu);

        // Criar a tabela de cursos
        TableView<Curso> tabela = new TableView<>();

        // Colunas da tabela
        TableColumn<Curso, String> colId = new TableColumn<>("ID Curso");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Curso, String> colNome = new TableColumn<>("Nome do curso");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Curso, String> colPeriodo = new TableColumn<>("Período");
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));

        tabela.getColumns().addAll(colId, colNome, colPeriodo);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Curso> dados = FXCollections.observableArrayList(
                new Curso("001", "Análise e Desenvolvimento de Sistemas", "Matutino"),
                new Curso("002", "Banco de Dados", "Noturno"),
                new Curso("003", "Desenvolvimento de Software Multiplataforma", "Matutino"),
                new Curso("004", "Gestão da Produção Industrial", "Noturno"),
                new Curso("005", "Gestão Empresarial", "EaD"),
                new Curso("006", "Logística", "Matutino / Noturno"),
                new Curso("007", "Manufatura Avançada", "Matutino"),
                new Curso("008", "Manutenção de Aeronaves", "Noturno"),
                new Curso("009", "Projetos de Estruturas Aeronáuticas", "Noturno")
        );
        tabela.setItems(dados);

        // Adiciona a tabela ao conteúdo principal
        contentArea.getChildren().add(tabela);

        // Definir o conteúdo central
        borderPane.setCenter(contentArea);

        // Menu lateral no lado esquerdo
        borderPane.setLeft(sideMenu);

        BorderPane.setMargin(sideMenu, new Insets(0, 20, 0, 0));

        // Criar a cena
        Scene scene = new Scene(borderPane, 1200, 800);
        stage.setTitle("Menu Lateral e Tabela");
        stage.setScene(scene);
        stage.show();

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}
