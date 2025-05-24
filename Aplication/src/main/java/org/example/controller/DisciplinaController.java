package org.example.controller;
//testar controller da disciplina!
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
import org.example.classes.Disciplina;
import org.example.classes.Professor;
import org.example.dao.DisciplinaDAO;

import java.util.List;
import java.util.Optional;


public class DisciplinaController {

    @FXML
    private Button btnAdicionarDisciplina;

    @FXML
    private Button btnCoordenador;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnDeletar;

    @FXML
    private ComboBox<Disciplina> cmbFiltro;

    @FXML
    private Button btnDisciplina;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnFiltro;

    @FXML
    private Button btnGradeHoraria;

    @FXML
    private Button btnProfessor;

    @FXML
    private TableColumn<Disciplina, String> nome;

    @FXML
    private TableColumn<Disciplina, Integer> professor;
    @FXML
    private TableColumn<Disciplina, Integer> curso;

    @FXML
    private TableColumn<Disciplina, Integer> semestre;

    @FXML
    private TableView<Disciplina> tblViewDisciplina;

    ObservableList<Professor> professorList = FXCollections.observableArrayList();

    DisciplinaDAO daoDisciplina = new DisciplinaDAO();

    List<Disciplina> disciplinas;

    Disciplina disciplina;
    private java.awt.Label textFieldNome;
    private java.awt.Label textFieldProfessor;
    private java.awt.Label textFieldCurso;
    private java.awt.Label textFieldSemestre;

    @FXML
    void adicionarDisciplina  (ActionEvent event) {
        Stage adicionarDisciplina = new Stage();

        VBox popupLayout = new VBox(10);
        popupLayout.setStyle("-fx-padding: 30px;" +
                "-fx-spacing: 10px;" +
                "-fx-pref-width: 400px;" +
                "-fx-pref-height: 300px;");

        adicionarDisciplina.setTitle("Adicionar Disciplina");

        Label labelNome = new Label("Nome da Disciplina");
        labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldNome = new TextField();
        textFieldNome.setPromptText("Nome da Disciplina");
        textFieldNome.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Label labelProfessor = new Label("Nome do Professor");
        labelProfessor.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldProfessor = new TextField();
        textFieldProfessor.setPromptText("Nome do Professor");
        textFieldProfessor.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Label labelCurso = new Label("Curso");
        labelCurso.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldCurso = new TextField();
        textFieldCurso.setPromptText("Curso");
        textFieldCurso.setStyle("-fx-font-size: 16px;" +
                "    -fx-border-color: #1D4ED8;" +
                "    -fx-border-width: 2px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-padding: 14px;" +
                "    -fx-focus-color: #1D4ED8;" +
                "    -fx-text-fill: #000000;" +
                "    -fx-pref-width: 240px;");

        Label labelSemestre = new Label("Semestre");
        labelSemestre.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        TextField textFieldSemestre = new TextField();
        textFieldSemestre.setPromptText("Semestre");
        textFieldSemestre.setStyle("-fx-font-size: 16px;" +
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
            Integer professor = Integer.parseInt(textFieldProfessor.getText());
            Integer curso  = Integer.parseInt(textFieldCurso.getText());
            Integer semestre  = Integer.parseInt(textFieldSemestre.getText());

            Disciplina disciplina = new Disciplina(nome, professor, curso, semestre);

            if (disciplina != null) {
                daoDisciplina.criarDisciplina(disciplina);
                atualizar();

                System.out.println("Disciplina cadastrada " + nome + " - " + professor + " - " + curso + " - " + semestre );
                adicionarDisciplina.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
                alert.showAndWait();
            }
        });

        HBox buttons = new HBox(10, cadastroButton);
        buttons.setAlignment(Pos.CENTER);

        popupLayout.getChildren().addAll(labelNome, textFieldNome, labelNome, textFieldProfessor, labelProfessor, textFieldCurso,
                labelCurso, textFieldSemestre, labelSemestre, buttons);

        Scene popupScene = new Scene(popupLayout, 510, 510);
        adicionarDisciplina.setScene(popupScene);
        adicionarDisciplina.show();
    }

    //  Método que atualiza a tabela
    public void atualizar(){
        // buscando disciplinas para colocar na lista observável
        List<Disciplina> disciList = daoDisciplina.listarTodos();
        professorList.clear(); //Limpando a lista
        //Adicionando novos dados a lista observável
        for(Disciplina disciplina: disciList){
            disciList.add(disciplina);
        }
        // Altera os itens da combobox
        cmbFiltro.getItems().clear();
        cmbFiltro.setPromptText("Filtrar");
        disciplinas = daoDisciplina.listarTodos();
        cmbFiltro.getItems().addAll(disciplinas);

        // Altera a tabela View
        tblViewDisciplina.setItems((ObservableList<Disciplina>) disciList);
    }

    @FXML
    void Filtrar(ActionEvent event) {
        Disciplina disciplinaSelecionada = cmbFiltro.getValue();

        if (disciplinaSelecionada != null) {
            List<Disciplina> listaNome = daoDisciplina.buscarPorNome(disciplinaSelecionada.getNome());
            ObservableList<Disciplina> filteredList = FXCollections.observableArrayList(listaNome);
            tblViewDisciplina.setItems(filteredList);
        } else {
            tblViewDisciplina.setItems(FXCollections.observableArrayList(daoDisciplina.listarTodos()));
        }
    }

    @FXML
    void removerFiltro(ActionEvent event) {
        atualizar();
    }

    // ALTERAR TODOS: Código de alterar discplina
    @FXML
    void UpdateDados(ActionEvent event) {
        if (disciplina != null) {
            Stage telaAtualizarDisciplina = new Stage();

            VBox popupLayout = new VBox(10);
            popupLayout.setStyle("-fx-padding: 30px;" +
                    "-fx-spacing: 10px;" +
                    "-fx-pref-width: 400px;" +
                    "-fx-pref-height: 300px;");

            telaAtualizarDisciplina.setTitle("Atualizar Disciplina");

            Label labelNome = new Label("Novo nome da Disciplina");
            labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            TextField textFieldNome = new TextField();
            textFieldNome.setPromptText("Nome da Disciplina");
            textFieldNome.setStyle("-fx-font-size: 16px;" +
                    "    -fx-border-color: #1D4ED8;" +
                    "    -fx-border-width: 2px;" +
                    "    -fx-border-radius: 8px;" +
                    "    -fx-background-radius: 8px;" +
                    "    -fx-padding: 14px;" +
                    "    -fx-focus-color: #1D4ED8;" +
                    "    -fx-text-fill: #000000;" +
                    "    -fx-pref-width: 240px;");

            Label labelProfessor = new Label("Novo nome dw Professor");
            labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            TextField textFieldProfessor = new TextField();
            textFieldNome.setPromptText("Nome de Professor");
            textFieldNome.setStyle("-fx-font-size: 16px;" +
                    "    -fx-border-color: #1D4ED8;" +
                    "    -fx-border-width: 2px;" +
                    "    -fx-border-radius: 8px;" +
                    "    -fx-background-radius: 8px;" +
                    "    -fx-padding: 14px;" +
                    "    -fx-focus-color: #1D4ED8;" +
                    "    -fx-text-fill: #000000;" +
                    "    -fx-pref-width: 240px;");

            Label labelCurso = new Label("Novo nome de Curso");
            labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            TextField textFieldCurso = new TextField();
            textFieldNome.setPromptText("Nome de Curso");
            textFieldNome.setStyle("-fx-font-size: 16px;" +
                    "    -fx-border-color: #1D4ED8;" +
                    "    -fx-border-width: 2px;" +
                    "    -fx-border-radius: 8px;" +
                    "    -fx-background-radius: 8px;" +
                    "    -fx-padding: 14px;" +
                    "    -fx-focus-color: #1D4ED8;" +
                    "    -fx-text-fill: #000000;" +
                    "    -fx-pref-width: 240px;");

            Label labelSemestre = new Label(" Novo Semestre");
            labelNome.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            TextField textFieldSemestre = new TextField();
            textFieldNome.setPromptText("Semestre");
            textFieldNome.setStyle("-fx-font-size: 16px;" +
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

            // Pegando dados da linha e colocando para serem alterados
            textFieldNome.setText(disciplina.getNome());
            textFieldNome.setText(disciplina.getNome());

            updateButton.setOnAction(e -> {

                // Coletando a alteração
                String nome = textFieldNome.getText();
                Integer idProfessor = Integer.parseInt(textFieldProfessor.getText());
                Integer idCurso  = Integer.parseInt(textFieldCurso.getText());
                Integer semestre  = Integer.parseInt(textFieldSemestre.getText());
                Disciplina disciplina = new Disciplina(nome, idProfessor, idCurso, semestre);



                if (!nome.isEmpty() && professor!= null  && curso!=null){
                    daoDisciplina.atualizarDisciplina(disciplina);
                    atualizar();

                    telaAtualizarDisciplina.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
                    alert.showAndWait();
                }
            });

            HBox buttons = new HBox(10, updateButton);
            buttons.setAlignment(Pos.CENTER);

            popupLayout.getChildren().addAll(labelNome, textFieldNome, labelNome, textFieldProfessor, labelProfessor, textFieldCurso,
                    labelCurso, textFieldSemestre, labelSemestre, buttons);

            Scene popupScene = new Scene(popupLayout, 510, 510);
            telaAtualizarDisciplina.setScene(popupScene);
            telaAtualizarDisciplina.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Escolha uma linha para alterar!");
            alert.setHeaderText("Erro ao alterar a Disciplina");
            alert.showAndWait();
        }
    }

    // ALTERAR TODOS: Método de coletar o dado da linha
    @FXML
    void coletarDadosLinha(MouseEvent event) {
        int i = tblViewDisciplina.getSelectionModel().getSelectedIndex();

        disciplina = tblViewDisciplina.getItems().get(i);
        System.out.println(disciplina);

    }
    // ALTERAR TODOS: Método de deletar e ter a confirmação
    @FXML
    void deletarProfessor(ActionEvent event) {
        if(disciplina != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer mesmo excluir?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Confirmação");
            alert.setTitle("Excluir Disciplina");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.YES){
                daoDisciplina.deleteDisciplina(disciplina.getId());
                disciplina = null;
                atualizar();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Escolha uma linha para deletar!");
            alert.setHeaderText("Erro ao deletar Disciplina");
            alert.showAndWait();
        }
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
        nome.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("nome"));
        professor.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("id_professor"));
        curso.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("id_curso"));
        semestre.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("semestre"));

        atualizar();
    }

public void atualizarDisciplinaList() {
    List<Disciplina> disciplinas = daoDisciplina.listarTodos();
    
    atualizarObservableList(disciplinas);
    updateFilterComboBox();
    updateTableView(disciplinas);
}

private void atualizarObservableList(List<Disciplina> disciplinas) {
    disciplinas.clear();
    disciplinas.addAll(disciplinas);
}

private void updateFilterComboBox() {
    cmbFiltro.getItems().clear();
    cmbFiltro.setPromptText("Filtrar");
    disciplinas = daoDisciplina.listarTodos();
    cmbFiltro.getItems().addAll(disciplinas);
}

private void updateTableView(List<Disciplina> disciplinesList) {
    tblViewDisciplina.setItems(FXCollections.observableArrayList(disciplinesList));
}
}