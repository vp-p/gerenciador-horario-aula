package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.example.classes.Professor;

import java.util.EventObject;


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
    private TableColumn<Professor, Integer> idProfessor;

    @FXML
    private TableColumn<Professor, String> nomeProfessor;

    @FXML
    private TableColumn<Professor, String> emailProfessor;

    @FXML
    private TableView<Professor> tblViewProfessor;

    @FXML
    void adicionarNovoProfessor(ActionEvent event) {

    }


    @FXML
    void mouseEntrou(MouseEvent event) {
        ((Region) event.getSource()).setStyle("-fx-background-color: #EAF2FFF;");
    }

    @FXML
    void mouseSaiu(MouseEvent event) {
        ((Region) event.getSource()).setStyle("-fx-background-color: transparent;");
    }
}
