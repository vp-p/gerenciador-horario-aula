package com.example.horarioaula;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class CadastrarDisciplina extends Application {

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

        // HeaderBox
        VBox headerBox = new VBox(5);
        VBox.setMargin(headerBox, new Insets(43, 0, 0, 0));
        contentArea.setSpacing(0);
        headerBox.setPadding(new Insets(20, 24, 20, 24));
        headerBox.getStyleClass().add("header-box");

        // Criar ícones para ações
        ImageView deletarIcon = new ImageView(new Image(getClass().getResource("/imgs/trash.png").toString()));
        ImageView filtrosIcon = new ImageView(new Image(getClass().getResource("/imgs/filtros.png").toString()));
        ImageView exportarIcon = new ImageView(new Image(getClass().getResource("/imgs/export.png").toString()));
        ImageView addIcon = new ImageView(new Image(getClass().getResource("/imgs/plus.png").toString()));

        // Ajustar o tamanho dos ícones
        for (ImageView iconAcoes : new ImageView[]{deletarIcon, filtrosIcon, exportarIcon, addIcon}) {
            iconAcoes.setFitWidth(20);
            iconAcoes.setFitHeight(20);
        }

        // Ações
        HBox actionButtons = new HBox(10);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);
        Button deletar = new Button("Deletar", deletarIcon);
        deletar.getStyleClass().add("botao-acao");
        Button filtros = new Button("Filtros", filtrosIcon);
        filtros.getStyleClass().add("botao-acao");
        Button exportar = new Button("Exportar", exportarIcon);
        exportar.getStyleClass().add("botao-acao");
        exportar.setStyle("-fx-border-color: #D0D5DD; -fx-text-fill: #344054;");
        Button adicionar = new Button("Adicionar disciplina", addIcon);
        adicionar.getStyleClass().add("botao-acao");
        adicionar.setStyle("-fx-background-color: #1D4ED8; -fx-text-fill: white; -fx-pref-width: 200px;");

        // Evento do botao adicionar semestre

        adicionar.setOnAction(e -> abrirPopupDisciplina());

        HBox.setHgrow(actionButtons, Priority.ALWAYS);

        // Titulo e subtitulo
        Label titulo = new Label ("Disciplinas");
        titulo.getStyleClass().add("titulo");
        Label subtitulo = new Label("A tabela de disciplinas cadastradas contém informações essenciais para organizar os cursos em níveis de aprendizado.");
        subtitulo.getStyleClass().add("subtitulo");

        VBox headerText = new VBox(5);
        headerText.setAlignment(Pos.CENTER_LEFT);
        headerText.getChildren().addAll(titulo, subtitulo);

        actionButtons.getChildren().addAll(deletar, filtros, exportar, adicionar);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);

        //HBox para textos e botoes
        HBox topContent = new HBox(20);
        topContent.setAlignment(Pos.CENTER_LEFT);
        topContent.getChildren().addAll(headerText, actionButtons);

        headerBox.getChildren().add(topContent);
        contentArea.getChildren().add(headerBox);

        // Criar a tabela de Disciplinas
        TableView<Disciplina> tabela = new TableView<>();

        // Colunas da tabela
        TableColumn<Disciplina, String> colId = new TableColumn<>("ID do Curso");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Disciplina, String> colNome = new TableColumn<>("Disciplina");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Disciplina, String> colCurso = new TableColumn<>("Curso");
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));

        TableColumn<Disciplina, String> colSemestre = new TableColumn<>("Semestre");
        colSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        // Adicionando todas as colunas à tabela
        tabela.getColumns().addAll(colId, colNome, colCurso, colSemestre);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Disciplina> dados = FXCollections.observableArrayList(
                new Disciplina("001", "Engenharia de Software", "Banco de Dados", "2º Semestre"),
                new Disciplina("001", "Banco de Dados", "Banco de Dados", "2º Semestre"),
                new Disciplina("001", "Planejamento Estratégico", "Banco de Dados", "2º Semestre"),
                new Disciplina("001", "Arquitura e modelagem de dados", "Banco de Dados", "2º Semestre"),
                new Disciplina("001", "Inglês", "Banco de Dados", "2º Semestre"),
                new Disciplina("001", "Fundamentos de Cálculo", "Banco de Dados", "2º Semestre")
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

    private void abrirPopupDisciplina() {
        Stage popup = new Stage();
        popup.setTitle("Adicionar Disciplina");

        VBox popupLayout = new VBox(10);
        popupLayout.getStyleClass().add("popup-container");

        // Criando os campos diretamente com os títulos
        Label labelNome = new Label("Disciplina:");
        TextField textNome = new TextField();
        textNome.getStyleClass().add("popup-textfield");
        textNome.setPromptText("Ex:Matemática Discreta");







        Label labelCurso = new Label("Curso:");
        labelCurso.getStyleClass().add("popup-label");
        ComboBox comboBoxCurso = new ComboBox();
        comboBoxCurso.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
        comboBoxCurso.setPromptText("Selecione o curso");

        comboBoxCurso.getItems().addAll("Banco de Dados", "Análise e Desenvolvimento de Sistemas", "Desenvolvimento de Software Multiplataforma",
                "Gestão Da Produção Industrial", "Logística", "Manufatura Avançada", "Manutenção de Aeronaves", "Projetos de Estruturas Aeronáuticas");




        Label labelSemestre = new Label("Semestre:");
        labelSemestre.getStyleClass().add("popup-label");
        ComboBox comboBoxSemestre = new ComboBox();
        comboBoxSemestre.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
        comboBoxSemestre.setPromptText("Selecione o Semestre");

        comboBoxSemestre.getItems().addAll("1° Semestre", "2 Semestre°", "3° Semestre",
                "4° Semestre", "5° Semestre", "6° Semestre");


        // Botão para cadastrar disciplina (ainda sem funcionalidade)
        Button cadastroButton = new Button("Cadastrar Disciplina");
        cadastroButton.getStyleClass().add("popup-button");

        // Layout dos botões
        HBox buttons = new HBox(10, cadastroButton);
        buttons.getStyleClass().add("popup-button-container");
        buttons.setAlignment(Pos.CENTER);

        // Adicionando todos os elementos ao layout
        popupLayout.getChildren().addAll(
                labelNome, textNome,
                labelCurso, comboBoxCurso,
                labelSemestre, comboBoxSemestre,
                buttons
        );

        Scene popupScene = new Scene(popupLayout, 500, 500);
        popupScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        popup.setScene(popupScene);
        popup.show();
    }


}

