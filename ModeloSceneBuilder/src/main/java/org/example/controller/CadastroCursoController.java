package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class CadastroCursoController {

    @FXML
    private Button btnAdicionarAula;

    @FXML
    private Button btnCoordenador;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnDisciplinas;

    @FXML
    private Button btnExportar;

    @FXML
    private Button btnFiltro;

    @FXML
    private Button btnGradeHoraria;

    @FXML
    private Button btnProfessor;

    @FXML
    private Button btnSemestres;

    @FXML
    private TableColumn<?, ?> tblColQuar;

    @FXML
    private TableColumn<?, ?> tblColQui;

    @FXML
    private TableColumn<?, ?> tblColSeg;

    @FXML
    private TableColumn<?, ?> tblColSex;

    @FXML
    private TableColumn<?, ?> tblColTer;

    @FXML
    private TableView<?> tblViewGrade;

    public void mouseEntrou(javafx.scene.input.MouseEvent mouseEvent) {
        ((Region) mouseEvent.getSource()).setStyle("-fx-background-color: #eaf2ff;");
    }

    public void mouseSaiu(javafx.scene.input.MouseEvent mouseEvent) {
        ((Region) mouseEvent.getSource()).setStyle("-fx-background-color: transparent;");
    }

}
