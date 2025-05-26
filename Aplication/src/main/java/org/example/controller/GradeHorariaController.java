package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.classes.*;
import org.example.dao.AulaDAO;
import org.example.dao.CursoDAO;
import org.example.dao.DisciplinaDAO;
import org.example.dao.ProfessorDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GradeHorariaController implements Initializable {

    @FXML
    private ComboBox<Curso> combocurso;

    @FXML
    private ComboBox<String> comboperiodo;

    @FXML
    private ComboBox<String> combosemestre;

    @FXML
    private Button btnatualizar;

    @FXML
    private TableColumn<LinhaGrade, String> tblColSeg;

    @FXML
    private TableColumn<LinhaGrade, String> tblColTer;

    @FXML
    private TableColumn<LinhaGrade, String> tblColQuar;

    @FXML
    private TableColumn<LinhaGrade, String> tblColQui;

    @FXML
    private TableColumn<LinhaGrade, String> tblColSex;

    @FXML
    private TableView<LinhaGrade> tblViewGrade;

    Aula aula;
    AulaDAO aulaDAO = new AulaDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CursoDAO cursoDAO = new CursoDAO();
            List<Curso> cursos = cursoDAO.listarTodos();
            combocurso.getItems().addAll(cursos);

            comboperiodo.getItems().addAll("Manhã", "Noite");
            combosemestre.getItems().addAll(
                    "1º Semestre", "2º Semestre", "3º Semestre",
                    "4º Semestre", "5º Semestre", "6º Semestre"
            );

            tblColSeg.setCellValueFactory(new PropertyValueFactory<>("segunda"));
            tblColTer.setCellValueFactory(new PropertyValueFactory<>("terca"));
            tblColQuar.setCellValueFactory(new PropertyValueFactory<>("quarta"));
            tblColQui.setCellValueFactory(new PropertyValueFactory<>("quinta"));
            tblColSex.setCellValueFactory(new PropertyValueFactory<>("sexta"));

            tblViewGrade.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    int idAula = newSelection.getIdAula();
                    Aula aulaSelecionada = aulaDAO.buscarPorId(idAula);
                    this.aula = aulaSelecionada;
                    System.out.println("Aula selecionada: " + aulaSelecionada);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ALTERAR TODOS: Método de deletar e ter a confirmação
    @FXML
    void deletarAula(ActionEvent event) {
        if (aula != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer mesmo excluir?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Confirmação");
            alert.setTitle("Excluir Aula");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                aulaDAO.marcarComoDeletada(aula.getIdAula()); // Método para marcar como deletada
                aula = null;
                atualizar(); // Atualiza a tabela após deletar
                Alert sucesso = new Alert(Alert.AlertType.INFORMATION, "Aula excluída com sucesso!");
                sucesso.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Escolha uma linha para deletar!");
            alert.setHeaderText("Erro ao deletar Aula");
            alert.showAndWait();
        }
    }


    @FXML
    public void AbrirPopUp() throws SQLException {
        Stage telaAdicionarAula = new Stage();
        telaAdicionarAula.setTitle("Adicionar Aula");

        VBox popupLayout = new VBox(15);
        popupLayout.setPadding(new Insets(30));
        popupLayout.setPrefWidth(400);
        popupLayout.setPrefHeight(500);
        popupLayout.setAlignment(Pos.TOP_LEFT);

        ComboBox<Curso> comboBoxCurso = new ComboBox<>();
        ComboBox<Disciplina> comboBoxDisciplina = new ComboBox<>();
        ComboBox<String> comboBoxDiaSemana = new ComboBox<>();
        ComboBox<String> comboBoxHorario = new ComboBox<>();
        Label labelProfessorESemestre = new Label("Professor e Semestre");

        CursoDAO cursoDAO = new CursoDAO();
        List<Curso> cursos = cursoDAO.listarTodos();
        comboBoxCurso.getItems().addAll(cursos);
        comboBoxDiaSemana.getItems().addAll("segunda", "terça", "quarta", "quinta", "sexta");
        comboBoxHorario.getItems().addAll("07:10", "08:00", "09:15", "10:05", "10:55", "11:45",
                "18:45", "19:35", "20:25","21:25","22:15");

        comboBoxCurso.setOnAction(e -> {
            Curso cursoSelecionado = comboBoxCurso.getValue();
            if (cursoSelecionado != null) {
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                List<Disciplina> disciplinas = disciplinaDAO.listarPorCurso(cursoSelecionado.getId());
                comboBoxDisciplina.getItems().clear();
                comboBoxDisciplina.getItems().addAll(disciplinas);
            }
        });

        comboBoxDisciplina.setOnAction(e -> {
            Disciplina disciplinaSelecionada = comboBoxDisciplina.getValue();
            if (disciplinaSelecionada != null) {
                ProfessorDAO professorDAO = new ProfessorDAO();
                Professor professor = professorDAO.buscarPorId(disciplinaSelecionada.getProfessor());
                if (professor != null) {
                    labelProfessorESemestre.setText("Professor e Semestre: "
                            + professor.getNomeProfessor() + ", "
                            + disciplinaSelecionada.getSemestre() + "° semestre");
                } else {
                    labelProfessorESemestre.setText("Professor não encontrado.");
                }
            }
        });

        Button btnCadastrarAula = new Button("Cadastrar Aula");
        btnCadastrarAula.setOnAction(e -> {
            try {
                CadastrarAula(
                        comboBoxCurso.getValue(),
                        comboBoxDisciplina.getValue(),
                        comboBoxDiaSemana.getValue(),
                        comboBoxHorario.getValue()
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro ao cadastrar aula");
                alert.setContentText("Verifique os campos e tente novamente.");
                alert.showAndWait();
            }
        });

        popupLayout.getChildren().addAll(
                new Label("Curso:"), comboBoxCurso,
                new Label("Disciplina:"), comboBoxDisciplina,
                new Label("Dia da Semana:"), comboBoxDiaSemana,
                new Label("Horário:"), comboBoxHorario,
                labelProfessorESemestre,
                btnCadastrarAula
        );

        Scene popupScene = new Scene(popupLayout);
        telaAdicionarAula.setScene(popupScene);
        telaAdicionarAula.show();
    }

    private void CadastrarAula(Curso curso, Disciplina disciplina, String diaSemana, String horario) throws Exception {
        if (curso == null || disciplina == null || diaSemana == null || horario == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }

        int numeroAula;
        String periodo;

        switch (horario) {
            case "07:10": numeroAula = 1; periodo = "Manhã"; break;
            case "08:00": numeroAula = 2; periodo = "Manhã"; break;
            case "09:15": numeroAula = 3; periodo = "Manhã"; break;
            case "10:05": numeroAula = 4; periodo = "Manhã"; break;
            case "10:55": numeroAula = 5; periodo = "Manhã"; break;
            case "11:45": numeroAula = 6; periodo = "Manhã"; break;
            case "18:45": numeroAula = 1; periodo = "Noite"; break;
            case "19:35": numeroAula = 2; periodo = "Noite"; break;
            case "20:25": numeroAula = 3; periodo = "Noite"; break;
            case "21:25": numeroAula = 4; periodo = "Noite"; break;
            case "22:15": numeroAula = 5; periodo = "Noite"; break;
            default: throw new IllegalArgumentException("Horário inválido: " + horario);
        }

        Aula aula = new Aula(0, disciplina.getProfessor(), disciplina.getId(), curso.getId(), diaSemana, numeroAula, null, periodo);
        AulaDAO auladao = new AulaDAO();
        auladao.criar(aula, disciplina.getSemestre());
    }

    public void atualizar() {
        try {
            AtualizaGrade();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AtualizaGrade() throws SQLException {
        Curso cursoSelecionado = combocurso.getValue();
        String periodoSelecionado = comboperiodo.getValue();
        String semestreSelecionado = combosemestre.getValue();

        if (cursoSelecionado != null && periodoSelecionado != null && semestreSelecionado != null) {
            int semestre = Integer.parseInt(semestreSelecionado.substring(0, 1));

            List<Aula> aulas = aulaDAO.listarAulasGrade(cursoSelecionado.getId(), semestre, periodoSelecionado);

            List<LinhaGrade> linhasGrade = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                linhasGrade.add(new LinhaGrade());
            }

            for (Aula aula : aulas) {
                if (!aula.isDeletado()) { // Filtra aulas não deletadas
                    int numeroAula = aula.getNumeroAula();
                    String diaSemana = aula.getDiaSemana();
                    String nomeDisciplina = aula.getNomeDisciplina();

                    LinhaGrade linhaGrade = linhasGrade.get(numeroAula - 1);

                    switch (diaSemana.toLowerCase()) {
                        case "segunda": linhaGrade.setSegunda(nomeDisciplina); break;
                        case "terça": linhaGrade.setTerca(nomeDisciplina); break;
                        case "quarta": linhaGrade.setQuarta(nomeDisciplina); break;
                        case "quinta": linhaGrade.setQuinta(nomeDisciplina); break;
                        case "sexta": linhaGrade.setSexta(nomeDisciplina); break;
                    }

                    linhaGrade.setIdAula(aula.getIdAula());
                }
            }

            tblViewGrade.getItems().setAll(linhasGrade);
        }
    }

}
