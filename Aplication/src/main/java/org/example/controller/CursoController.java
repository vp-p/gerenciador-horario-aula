package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.example.classes.Curso;
import org.example.dao.CursoDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CursoController implements Initializable {

    @FXML
    private ComboBox<Curso> combocurso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CursoDAO cursoDAO = new CursoDAO();
            List<Curso> cursos = cursoDAO.listarTodos();
            combocurso.getItems().addAll(cursos);
            System.out.println(cursos);
            System.out.println("taaaaaaaaaaaaaaaaaaaaaaa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
