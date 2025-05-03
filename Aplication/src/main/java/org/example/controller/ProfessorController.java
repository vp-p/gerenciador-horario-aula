package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.classes.Professor;
import org.example.dao.ProfessorDAO;

import java.util.List;


public class ProfessorController {

    @FXML
    private Button btnAdicionarProfessor;

    @FXML
    private Button btnCoordenador;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnDeletar;

    @FXML
    private ComboBox<Professor> cmbFiltro;

    @FXML
    private Button btnDisciplinas;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnFiltro;

    @FXML
    private Button btnGradeHoraria;

    @FXML
    private Button btnProfessor;


    @FXML
    private TableColumn<Professor, Long> idProfessor;

    @FXML
    private TableColumn<Professor, String> nomeProfessor;

    @FXML
    private TableColumn<Professor, String> emailProfessor;

    @FXML
    private TableView<Professor> tblViewProfessor;

    @FXML
    private TextField txtEmailProfessor;

    @FXML
    private TextField txtNomeProfessor;

    ObservableList<Professor> professorList = FXCollections.observableArrayList();

    ProfessorDAO daoProfessor = new ProfessorDAO();

    List<Professor> professorcmb;

    @FXML
    void adicionarNovoProfessor  (ActionEvent event) {
        Stage telaAdicionarProfessor = new Stage();

        VBox popupLayout = new VBox(10);
       popupLayout.setStyle("-fx-padding: 30px;" +
        "-fx-spacing: 10px;" +
        "-fx-pref-width: 400px;" +
        "-fx-pref-height: 300px;");

        telaAdicionarProfessor.setTitle("Adicionar Professor");

        Label labelNome = new Label("Nome do Professor");
        labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldNome = new TextField();
        textFieldNome.setPromptText("Nome do Professor");
        textFieldNome.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Label labelEmail = new Label("Email do Professor");
        labelEmail.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldEmail = new TextField();
        textFieldEmail.setPromptText("Email do Professor");
        textFieldEmail.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Button cadastroButton = new Button("Cadastrar");
        cadastroButton.setStyle("-fx-background-color: #1D4ED8;" +
                "    -fx-font-weight: bold;" +
                "    -fx-text-fill: white;" +
                "    -fx-padding: 10px 20px;" +
                "    -fx-font-size: 18px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-cursor: hand;" +
                "    -fx-pref-width: 340px;" +
                "    -fx-pref-height: 50px;" +
                "    -fx-alignment: CENTER;");

        // Aqui você pode adicionar a lógica do botão "Cadastrar"
        cadastroButton.setOnAction(e -> {

             String nome = textFieldNome.getText();
             String email = textFieldEmail.getText();
            if (!nome.isEmpty() && !email.isEmpty()) {
                daoProfessor.criarProfessor(nome, email);
                atualizar();

            System.out.println("Professor cadastrado " + nome + " - " + email );
            telaAdicionarProfessor.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
                alert.showAndWait();
            }
        });

        HBox buttons = new HBox(10, cadastroButton);
        buttons.setAlignment(Pos.CENTER);

        popupLayout.getChildren().addAll(labelNome, textFieldNome, labelEmail, textFieldEmail, buttons);

        Scene popupScene = new Scene(popupLayout, 510, 510);
        telaAdicionarProfessor.setScene(popupScene);
        telaAdicionarProfessor.show();
    }


    @FXML
    void deletarProfessor(ActionEvent event) {

        Stage telaDeletarProfessor = new Stage();

        VBox popupLayout = new VBox(10);
        popupLayout.setStyle("-fx-padding: 30px;" +
                "-fx-spacing: 10px;" +
                "-fx-pref-width: 400px;" +
                "-fx-pref-height: 300px;");

        telaDeletarProfessor.setTitle("Deletar Professor");

        Label labelId = new Label("Id do Professor");
        labelId.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldId = new TextField();
        textFieldId.setPromptText("Id do professor");
        textFieldId.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");


        Button deletarButton = new Button("Deletar");
        deletarButton.setStyle("-fx-background-color: #1D4ED8;" +
                "    -fx-font-weight: bold;" +
                "    -fx-text-fill: white;" +
                "    -fx-padding: 10px 20px;" +
                "    -fx-font-size: 18px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-cursor: hand;" +
                "    -fx-pref-width: 340px;" +
                "    -fx-pref-height: 50px;" +
                "    -fx-alignment: CENTER;");

        deletarButton.setOnAction(e -> {

            Integer id_professor = Integer.parseInt(textFieldId.getText());

            daoProfessor.deletar(id_professor);

            atualizar();

            telaDeletarProfessor.close();
        });

        HBox buttons = new HBox(10, deletarButton);
        buttons.setAlignment(Pos.CENTER);

        popupLayout.getChildren().addAll(labelId, textFieldId, buttons);

        Scene popupScene = new Scene(popupLayout, 510, 510);
        telaDeletarProfessor.setScene(popupScene);
        telaDeletarProfessor.show();


    }

    public void atualizar(){
    // buscando professores para colocar na lista observável
        List<Professor> PrfList = daoProfessor.buscarProfessores();
        professorList.clear(); //Limpando a lista
        //Adicionando novos dados a lista observável
        for(Professor professor: PrfList){
         professorList.add(professor);
        }
        // Altera os itens da combobox
        cmbFiltro.getItems().clear();
        cmbFiltro.setPromptText("Filtrar");
        professorcmb = daoProfessor.buscarProfessores();
        cmbFiltro.getItems().addAll(professorcmb);

        // Altera a tabela View
        tblViewProfessor.setItems(professorList);
    }

    @FXML
    void Filtrar(ActionEvent event) {
        // Busca o valor selecionado
        Professor professor = cmbFiltro.getValue();
        List<Professor> listaNome = daoProfessor.buscarPorNome(professor.getNomeProfessor());
        professorList.clear();
        for(Professor professorNome: listaNome){
            professorList.add(professorNome);
        }
        tblViewProfessor.setItems(professorList);

    }

    @FXML
    void removerFiltro(ActionEvent event) {
        atualizar();
    }

    @FXML
    void UpdateDados(ActionEvent event) {
        Stage telaAtualizarProfessor = new Stage();

        VBox popupLayout = new VBox(10);
        popupLayout.setStyle("-fx-padding: 30px;" +
                "-fx-spacing: 10px;" +
                "-fx-pref-width: 400px;" +
                "-fx-pref-height: 300px;");

        telaAtualizarProfessor.setTitle("Atualizar Professor");

        Label labelId = new Label("Id Professor");
        labelId.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldId = new TextField();
        textFieldId.setPromptText("Informe o id que deseja alterar");
        textFieldId.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Label labelNome = new Label("Novo nome do Professor");
        labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldNome = new TextField();
        textFieldNome.setPromptText("Nome do Professor");
        textFieldNome.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Label labelEmail = new Label("Novo email do Professor");
        labelEmail.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldEmail = new TextField();
        textFieldEmail.setPromptText("Email do Professor");
        textFieldEmail.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Button updateButton = new Button("Atualizar");
        updateButton.setStyle("-fx-background-color: #1D4ED8;" +
                "    -fx-font-weight: bold;" +
                "    -fx-text-fill: white;" +
                "    -fx-padding: 10px 20px;" +
                "    -fx-font-size: 18px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-cursor: hand;" +
                "    -fx-pref-width: 340px;" +
                "    -fx-pref-height: 50px;" +
                "    -fx-alignment: CENTER;");

        // Aqui você pode adicionar a lógica do botão "Cadastrar"
        updateButton.setOnAction(e -> {
            Integer id = Integer.parseInt(textFieldId.getText());
            String nome = textFieldNome.getText();
            String email = textFieldEmail.getText();
            if (!nome.isEmpty() && !email.isEmpty()) {
                daoProfessor.alterarDados(id,nome, email);
                atualizar();

                telaAtualizarProfessor.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
                alert.showAndWait();
            }
        });

        HBox buttons = new HBox(10, updateButton);
        buttons.setAlignment(Pos.CENTER);

        popupLayout.getChildren().addAll(labelId,textFieldId,labelNome, textFieldNome, labelEmail, textFieldEmail, buttons);

        Scene popupScene = new Scene(popupLayout, 510, 510);
        telaAtualizarProfessor.setScene(popupScene);
        telaAtualizarProfessor.show();
    }

    @FXML
    void mouseEntrou(MouseEvent event) {
        ((Region) event.getSource()).setStyle("-fx-background-color: #EAF2FFF;");
    }

    @FXML
    void mouseSaiu(MouseEvent event) {
        ((Region) event.getSource()).setStyle("-fx-background-color: transparent;");
    }

    @FXML
    public void initialize(){
       idProfessor.setCellValueFactory(new PropertyValueFactory<Professor, Long>("id"));
       nomeProfessor.setCellValueFactory(new PropertyValueFactory<Professor, String>("nomeProfessor"));
       emailProfessor.setCellValueFactory(new PropertyValueFactory<Professor, String>("emailProfessor"));

        atualizar();
    }
}
