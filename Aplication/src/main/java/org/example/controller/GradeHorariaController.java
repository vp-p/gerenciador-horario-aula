
package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.classes.*;
import org.example.dao.AulaDAO;
import org.example.dao.CursoDAO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.dao.DisciplinaDAO;
import org.example.dao.ProfessorDAO;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

            // Configura as colunas para puxar os dados corretos
            tblColSeg.setCellValueFactory(new PropertyValueFactory<>("segunda"));
            tblColTer.setCellValueFactory(new PropertyValueFactory<>("terca"));
            tblColQuar.setCellValueFactory(new PropertyValueFactory<>("quarta"));
            tblColQui.setCellValueFactory(new PropertyValueFactory<>("quinta"));
            tblColSex.setCellValueFactory(new PropertyValueFactory<>("sexta"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void AbrirPopUp() throws SQLException {
        System.out.println("abrir popup");

        Stage telaAdicionarAula = new Stage();
        telaAdicionarAula.setTitle("Adicionar Aula");

        VBox popupLayout = new VBox(15); // espaçamento entre os campos
        popupLayout.setPadding(new Insets(30));
        popupLayout.setPrefWidth(400);
        popupLayout.setPrefHeight(500);
        popupLayout.setAlignment(Pos.TOP_LEFT); // alinhamento geral

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

        // Quando selecionar um curso
        comboBoxCurso.setOnAction(e -> {
            Curso cursoSelecionado = comboBoxCurso.getValue();
            if (cursoSelecionado != null) {
                System.out.println("Curso selecionado: " + cursoSelecionado);

                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                List<Disciplina> disciplinas = disciplinaDAO.listarPorCurso(cursoSelecionado.getId());
                System.out.println(disciplinas);
                comboBoxDisciplina.getItems().clear();
                comboBoxDisciplina.getItems().addAll(disciplinas);
            }
        });

        // Quando selecionar uma disciplina
        comboBoxDisciplina.setOnAction(e -> {
            Disciplina disciplinaSelecionada = comboBoxDisciplina.getValue();
            if (disciplinaSelecionada != null) {
                System.out.println("Disciplina selecionada: " + disciplinaSelecionada);

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

        // Botão "Cadastrar Aula"
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
                btnCadastrarAula // Adiciona o botão ao layout
        );

        Scene popupScene = new Scene(popupLayout);
        telaAdicionarAula.setScene(popupScene);
        telaAdicionarAula.show();
    }

    // Função para cadastrar a aula
    private void CadastrarAula(Curso curso, Disciplina disciplina, String diaSemana, String horario) throws Exception {
        if (curso == null || disciplina == null || diaSemana == null || horario == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }




        System.out.println("Cadastrando aula...");
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Disciplina: " + disciplina.getNome());
        System.out.println("Dia da Semana: " + diaSemana);
        System.out.println("Horário: " + horario);
        int numeroAula;
        String periodo;

        switch (horario) {
            case "07:10":
                numeroAula = 1;
                periodo = "Manhã";
                break;
            case "08:00":
                numeroAula = 2;
                periodo = "Manhã";
                break;
            case "09:15":
                numeroAula = 3;
                periodo = "Manhã";
                break;
            case "10:05":
                numeroAula = 4;
                periodo = "Manhã";
                break;
            case "10:55":
                numeroAula = 5;
                periodo = "Manhã";
                break;
            case "11:45":
                numeroAula = 6;
                periodo = "Manhã";
                break;
            case "18:45":
                numeroAula = 1;
                periodo = "Noite";
                break;
            case "19:35":
                numeroAula = 2;
                periodo = "Noite";
                break;
            case "20:25":
                numeroAula = 3;
                periodo = "Noite";
                break;
            case "21:25":
                numeroAula = 4;
                periodo = "Noite";
            case "22:15":
                numeroAula = 5;
                periodo = "Noite";
                break;
            default:
                throw new IllegalArgumentException("Horário inválido: " + horario);
        }
        System.out.println(disciplina);
        Aula aula = new Aula(0, disciplina.getProfessor(),disciplina.getId(), curso.getId(),diaSemana ,numeroAula, null, periodo);
//                                int idAula, int idProfessor, int idDisciplina, int idCurso, String diaSemana, int numeroAula, String nomeDisciplina, String periodo
        AulaDAO auladao = new AulaDAO();
        auladao.criar(aula, disciplina.getSemestre());
        // Aqui você pode implementar a lógica para salvar no banco de dados
        // Exemplo: Chamar um método em AulaDAO para inserir a aula
        // AulaDAO aulaDAO = new AulaDAO();
        // aulaDAO.criarAula(curso.getId(), disciplina.getId(), diaSemana, horario);


    }


    @FXML
    private void AtualizaGrade() throws SQLException {
        Curso cursoSelecionado = combocurso.getValue();
        String periodoSelecionado = comboperiodo.getValue();
        String semestreSelecionado = combosemestre.getValue();

        if (cursoSelecionado != null && periodoSelecionado != null && semestreSelecionado != null) {
            int semestre = Integer.parseInt(semestreSelecionado.substring(0, 1));

            AulaDAO aulaDAO = new AulaDAO();
            List<Aula> aulas = aulaDAO.listarAulasGrade(
                    cursoSelecionado.getId(), semestre, periodoSelecionado
            );

            // Criar 6 linhas (1 linha para cada horário de aula)
            List<LinhaGrade> linhasGrade = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                linhasGrade.add(new LinhaGrade());
            }

            // Preencher as linhas com base nas aulas recebidas
            for (Aula aula : aulas) {
                int numeroAula = aula.getNumeroAula(); // 1 a 6
                String diaSemana = aula.getDiaSemana(); // "segunda", "terça", etc
                String nomeDisciplina = aula.getNomeDisciplina(); // <-- Já no objeto Aula

                LinhaGrade linhaGrade = linhasGrade.get(numeroAula - 1); // (posição correta)

                switch (diaSemana.toLowerCase()) {
                    case "segunda":
                        linhaGrade.setSegunda(nomeDisciplina);
                        break;
                    case "terca":
                        linhaGrade.setTerca(nomeDisciplina);
                        break;
                    case "quarta":
                        linhaGrade.setQuarta(nomeDisciplina);
                        break;
                    case "quinta":
                        linhaGrade.setQuinta(nomeDisciplina);
                        break;
                    case "sexta":
                        linhaGrade.setSexta(nomeDisciplina);
                        break;
                    default:
                        System.out.println("Dia da semana inválido: " + diaSemana);
                        break;
                }
            }

            tblViewGrade.getItems().setAll(linhasGrade);

        } else {
            System.out.println("Selecione todos os filtros primeiro!");
        }
    }
}
