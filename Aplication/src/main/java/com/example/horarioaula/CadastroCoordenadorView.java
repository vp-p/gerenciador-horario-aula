package com.example.horarioaula;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CadastroCoordenadorView extends Application {

   public void start(Stage stage){
        //Painel principal do Cadastro do coordenador
            BorderPane telaCD = new BorderPane();
            Scene scene  = new Scene(telaCD,1500,600);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setTitle("Cadastro de Coordenadores");
            stage.setScene(scene);
            stage.show();

            VBox sideMenu = new VBox(10);
            sideMenu.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, -4, 4);");

            telaCD.setLeft(sideMenu);

             ImageView logo = new ImageView(new Image(getClass().getResource("/imgs/Sinapse.png").toString()));
             logo.setFitWidth(124);
             logo.setFitHeight(35);
             sideMenu.getChildren().add(logo);

            // ícones do menu
            ImageView icon1 = new ImageView(new Image(getClass().getResource("/imgs/Calendar.png").toString())) ;
            ImageView icon2 = new ImageView(new Image(getClass().getResource("/imgs/User.png").toString()));
            ImageView icon3 = new ImageView(new Image(getClass().getResource("/imgs/FolderUser.png").toString()));
            ImageView icon4 = new ImageView(new Image(getClass().getResource("/imgs/red.png").toString()));
            ImageView icon5 = new ImageView(new Image(getClass().getResource("/imgs/blue.png").toString()));
            ImageView icon6 = new ImageView(new Image(getClass().getResource("/imgs/purple.png").toString()));

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

       HBox topMenuCD = new HBox();
       topMenuCD.setAlignment(Pos.CENTER_RIGHT);
       topMenuCD.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px;");
       HBox.setMargin(topMenuCD, new Insets(0, 0, 20, 0));

       HBox.setMargin(topMenuCD, new Insets(0, 0, 20, 0));

       ImageView profileImage = new ImageView(new Image(getClass().getResource("/imgs/perfil.png").toString()));
       profileImage.setFitWidth(40);
       profileImage.setFitHeight(40);
       Circle clip = new Circle(20, 20, 20);
       profileImage.setClip(clip);
       topMenuCD.getChildren().add(profileImage);

       // Conteudo principal (Menu e a Tabela com os dados dos coordenadores)
       VBox contentArea = new VBox();
       contentArea.setSpacing(10);
       contentArea.setPadding(new Insets(20, 10, 10, 10));
       contentArea.getChildren().add(topMenuCD);

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

       HBox actionButtons = new HBox(10);
       actionButtons.setAlignment(Pos.CENTER_RIGHT);
       Button deletar = new Button("Deletar", deletarIcon);
       deletar.getStyleClass().add("botao-acao");
       Button filtros = new Button("Filtros", filtrosIcon);
       filtros.getStyleClass().add("botao-acao");
       Button exportar = new Button("Exportar", exportarIcon);
       exportar.getStyleClass().add("botao-acao");
       exportar.setStyle("-fx-border-color: #D0D5DD; -fx-text-fill: #344054;");
       Button adicionar = new Button("Adicionar coordenador", addIcon);
       adicionar.getStyleClass().add("botao-acao");
       adicionar.setStyle("-fx-background-color: #1D4ED8; -fx-text-fill: white; -fx-pref-width: 200px;");

       CadastroCoordenadorController controller = new CadastroCoordenadorController();
       adicionar.setOnAction(controller.PopupCoordenador());

       HBox.setHgrow(actionButtons, Priority.ALWAYS);

       // Titulo e subtitulo
       Label titulo = new Label ("Coordenadores");
       titulo.getStyleClass().add("titulo");
       Label subtitulo = new Label("A tabela de coordenadores para a consulta.");
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

       // Colunas da tabela
       TableView<Coordenador> tabela = new TableView<>();

       TableColumn<Coordenador, String> colNome = new TableColumn<>("Nome do coordenador");
       colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

       TableColumn<Coordenador, String> colEmail = new TableColumn<>("Email");
       colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

       TableColumn<Coordenador, String> colCurso = new TableColumn<>("Curso");
       colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));

       tabela.getColumns().addAll(colNome, colEmail, colCurso);
       tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

       ObservableList<Coordenador> dados = FXCollections.observableArrayList(
               new Coordenador("Fabiano Sabha Walczak", "fsabha@fatec.sp.gov.br", "Banco de Dados"),
               new Coordenador("José Walmir Gonçalves Duque", "walmir.duque@fatec.sp.gov.br", "Análise e Desenvolvimento de Sistemas"),
               new Coordenador("Reinaldo Gen Ichiro Arakaki", "reinaldo.arakaki@fatec.sp.gov.br", "Desenvolvimento de Software Multiplataforma"),
               new Coordenador("Carlos Lineu de Faria e Alves", "carlos.lineu@fatec.sp.gov.br", "Gestão Da Produção Industrial"),
               new Coordenador("Marcus Vinícius do Nascimento", "nascimento.mv@fatec.sp.gov.br", "Logística"),
               new Coordenador("Viviane Ribeiro de Siqueira", "viviane.siqueira@fatec.sp.gov.br", "Manufatura Avançada"),
               new Coordenador("Roque Antônio de Moura", "roque.moura@fatec.sp.gov.br", "Manutenção de Aeronaves"),
               new Coordenador("Reinaldo Fagundes dos Santos", "reinaldo.fs@fatec.sp.gov.br", "Projetos de Estruturas Aeronáuticas")
       );
       tabela.setItems(dados);

       contentArea.getChildren().add(tabela);

       telaCD.setCenter(contentArea);

       telaCD.setLeft(sideMenu);

       telaCD.setMargin(sideMenu, new Insets(0, 20, 0, 0));

   }

   public static void main(String [] args){
       launch();
   }

}
