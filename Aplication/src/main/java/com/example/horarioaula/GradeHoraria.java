package com.example.horarioaula;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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

public class GradeHoraria extends Application {
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
        Button btn4 = new Button("Aulas", icon4);
        Button btn5 = new Button("Semestres", icon5);
        Button btn6 = new Button("Aulas", icon6);

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

        Button adicionar = new Button("Adicionar Aula", addIcon);
        adicionar.getStyleClass().add("botao-acao");
        adicionar.setStyle("-fx-background-color: #1D4ED8; -fx-text-fill: white; -fx-pref-width: 200px;");

        // Evento do botão "Adicionar Aula"
        adicionar.setOnAction(e -> PopupAula());

        HBox.setHgrow(actionButtons, Priority.ALWAYS);

        // Título e subtítulo
        Label titulo = new Label("Grade Horaria");
        titulo.getStyleClass().add("titulo");
        Label subtitulo = new Label("Grade com as aulas de cada dia da semana.");
        subtitulo.getStyleClass().add("subtitulo");

        VBox headerText = new VBox(5);
        headerText.setAlignment(Pos.CENTER_LEFT);
        headerText.getChildren().addAll(titulo, subtitulo);

        actionButtons.getChildren().addAll(deletar, filtros, exportar, adicionar);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);

        // HBox para textos e botões
        HBox topContent = new HBox(20);
        topContent.setAlignment(Pos.CENTER_LEFT);
        topContent.getChildren().addAll(headerText, actionButtons);

        // Criar ComboBox para selecionar curso
        ComboBox<String> cursoComboBox = new ComboBox<>();
        cursoComboBox.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
        cursoComboBox.setPromptText("Selecione um curso");
        cursoComboBox.setItems(FXCollections.observableArrayList(
                "Ciência da Computação", "Engenharia de Software", "Sistemas de Informação",
                "Análise e Desenvolvimento de Sistemas", "Engenharia da Computação"
        ));
        cursoComboBox.setStyle("-fx-background-color: white; -fx-padding: 5px;");

        // Definir largura da ComboBox
        cursoComboBox.setPrefWidth(250);

        // Adicionar ao headerText
        headerText.getChildren().add(cursoComboBox);

        headerBox.getChildren().add(topContent);
        contentArea.getChildren().add(headerBox);

        // Criar a tabela
        TableView<ObservableList<String>> tabela = new TableView<>();

        // Criar colunas para os dias da semana
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        for (int i = 0; i < dias.length; i++) {
            final int colunaIndex = i;
            TableColumn<ObservableList<String>, String> coluna = new TableColumn<>(dias[i]);
            coluna.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colunaIndex)));
            tabela.getColumns().add(coluna);
        }

        // Criar dados para a tabela (cada linha é uma lista de disciplinas)
        ObservableList<ObservableList<String>> dados = FXCollections.observableArrayList(
                FXCollections.observableArrayList("Redes", "SO", "LP", "Matemática", "Inglês"),
                FXCollections.observableArrayList("BD", "ED", "Física", "Química", "História"),
                FXCollections.observableArrayList("Álgebra", "Cálculo", "Programação", "Geografia", "Filosofia")
        );
        tabela.setItems(dados);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Permitir seleção de células individuais
        tabela.getSelectionModel().setCellSelectionEnabled(true);

        // Adicionar funcionalidade ao botão "Deletar"
        deletar.setOnAction(e -> {
            // Obter a célula selecionada na tabela
            TablePosition<?, ?> selectedCell = tabela.getSelectionModel().getSelectedCells().stream()
                    .findFirst()
                    .orElse(null);

            if (selectedCell == null) {
                // Se nenhuma célula estiver selecionada, exibir um alerta
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nenhuma célula selecionada");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, selecione uma célula para excluir.");
                alert.showAndWait();
                return;
            }

            // Obter a linha e coluna da célula selecionada
            int row = selectedCell.getRow();
            int column = selectedCell.getColumn();

            // Confirmar exclusão com um popup
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmação de Exclusão");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Deseja realmente excluir o conteúdo desta célula?");

            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Excluir o conteúdo da célula
                    ObservableList<String> rowData = tabela.getItems().get(row);
                    rowData.set(column, ""); // Define o valor da célula como vazio
                }
            });
        });

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

    // Método para abrir popup
    private void PopupAula() {
        Stage popup = new Stage();
        popup.setTitle("Adicionar Aula");
        VBox popupLayout = new VBox(10);
        popupLayout.getStyleClass().add("popup-container");

        // Label e ComboBox para Disciplina
        Label disciplinaLabel = new Label("Disciplina:");
        disciplinaLabel.getStyleClass().add("popup-label");
        ComboBox<String> disciplinaCombo = new ComboBox<>();
        disciplinaCombo.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
        disciplinaCombo.setItems(FXCollections.observableArrayList(
                "Matemática", "Física", "Programação", "Banco de Dados", "Engenharia de Software"
        ));
        disciplinaCombo.getStyleClass().add("popup-combobox");

        // Label e ComboBox para Professor
        Label professorLabel = new Label("Professor:");
        professorLabel.getStyleClass().add("popup-label");
        ComboBox<String> professorCombo = new ComboBox<>();
        professorCombo.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
        professorCombo.setItems(FXCollections.observableArrayList(
                "Prof. João", "Prof. Maria", "Prof. Carlos", "Prof. Ana", "Prof. Roberto"
        ));
        professorCombo.getStyleClass().add("popup-combobox");

        // Label e ComboBox para Horário
        Label horarioLabel = new Label("Horário:");
        horarioLabel.getStyleClass().add("popup-label");
        ComboBox<String> horarioCombo = new ComboBox<>();
        horarioCombo.setStyle("-fx-pref-width: 500px; -fx-border-radius: 8px; -fx-font-size: 13px; -fx-text-fill: #333333; -fx-padding: 14px; -fx-border-width: 2px; -fx-border-color: #1D4ED8; -fx-background-color: #FFFFFF; -fx-text-alignment: left;");
        horarioCombo.setItems(FXCollections.observableArrayList(
                "07:00 - 07:50", "08:00 - 08:50", "09:00 - 09:50",
                "10:00 - 10:50", "11:00 - 11:50", "13:00 - 13:50",
                "14:00 - 14:50", "15:00 - 15:50", "16:00 - 16:50"
        ));
        horarioCombo.getStyleClass().add("popup-combobox");

        // Botão para cadastrar a aula
        Button cadastroButton = new Button("Cadastrar Aula");
        cadastroButton.getStyleClass().add("popup-button");
        HBox buttons = new HBox(10, cadastroButton);
        buttons.getStyleClass().add("popup-button-container");

        // Adicionando os componentes ao layout
        popupLayout.getChildren().addAll(
                disciplinaLabel, disciplinaCombo,
                professorLabel, professorCombo,
                horarioLabel, horarioCombo,
                buttons
        );

        // Criando a cena e aplicando o CSS
        Scene popupScene = new Scene(popupLayout, 350, 300);
        popupScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        popup.setScene(popupScene);
        popup.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}