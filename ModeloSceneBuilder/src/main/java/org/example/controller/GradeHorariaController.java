package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GradeHorariaController {

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
    private Button btnFiltro;

    @FXML
    private Button btnGradeHoraria;

    @FXML
    private Button btnProfessor;

    @FXML
    private Button btnSemestres;

    @FXML
    private TableColumn<org.example.classes.Aula, String> tblColQuar;

    @FXML
    private TableColumn<org.example.classes.Aula, String> tblColQui;

    @FXML
    private TableColumn<org.example.classes.Aula, String> tblColSeg;

    @FXML
    private TableColumn<org.example.classes.Aula, String> tblColSex;

    @FXML
    private TableColumn<org.example.classes.Aula, String> tblColTer;

    @FXML
    private TableView<org.example.classes.Aula> tblViewGrade;

}
