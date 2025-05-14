package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.example.dao.ProfessorDAO;
import org.example.classes.Professor;

import java.util.List;

public class PopupCursoController {

    @FXML private TextField txtNomeCurso;
    @FXML private ComboBox<String> comboCoordenador;
    @FXML private ComboBox<String> comboPeriodo;
    @FXML private Button btnConfirmar;
    @FXML private Button btnCancelar;

    private boolean confirmado = false;

    @FXML
    private void initialize() {
        try {
            List<Professor> profs = new ProfessorDAO().buscarProfessores();
            for (Professor p : profs) {
                comboCoordenador.getItems().add(p.getNomeProfessor());
            }

            // Adiciona opções de período
            comboPeriodo.getItems().addAll("Matutino", "Noturno" );

        } catch (Exception e) {
            exibirErro("Erro ao carregar dados: " + e.getMessage());
        }
    }

    @FXML
    private void confirmar() {
        if (txtNomeCurso.getText().isEmpty() || comboCoordenador.getValue() == null || comboPeriodo.getValue() == null) {
            exibirErro("Preencha todos os campos!");
            return;
        }

        confirmado = true;
        fechar();
    }

    @FXML
    private void cancelar() {
        confirmado = false;
        fechar();
    }

    private void exibirErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void fechar() {
        Stage stage = (Stage) btnConfirmar.getScene().getWindow();
        stage.close();
    }

    // Getters
    public boolean isConfirmado() {
        return confirmado;
    }

    public String getCursoSelecionado() {
        return txtNomeCurso.getText();
    }

    public String getCoordenadorSelecionado() {
        return comboCoordenador.getValue();
    }

    public String getPeriodoSelecionado() {
        return comboPeriodo.getValue();
    }
}
