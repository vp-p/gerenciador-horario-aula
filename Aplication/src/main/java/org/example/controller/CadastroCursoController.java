package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dao.CursoDAO;
import org.example.classes.Curso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CadastroCursoController {

    @FXML private TableView<Curso> tblViewCurso;
    @FXML private TableColumn<Curso, Boolean> tblSelecionarCurso;
    @FXML private TableColumn<Curso, Integer> tblIdCurso;
    @FXML private TableColumn<Curso, String> tblNomeCurso;
    @FXML private TableColumn<Curso, String> tblCoordenador;
    @FXML private TableColumn<Curso, String> tblPeriodo;

    private final CursoDAO cursoDAO = new CursoDAO();
    private final ObservableList<Curso> cursos = FXCollections.observableArrayList();
    private final FilteredList<Curso> cursosFiltrados = new FilteredList<>(cursos, p -> true);
    private String filtroAtual = "";

    @FXML
    public void initialize() {
        configurarTabela();
        carregarCursosDoBanco();
    }

    private void configurarTabela() {
        tblViewCurso.setEditable(true);
        tblSelecionarCurso.setEditable(true);

        tblSelecionarCurso.setCellValueFactory(cd -> cd.getValue().selecionadoProperty());
        tblSelecionarCurso.setCellFactory(CheckBoxTableCell.forTableColumn(tblSelecionarCurso));

        tblIdCurso.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNomeCurso.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblCoordenador.setCellValueFactory(new PropertyValueFactory<>("coordenador"));
        tblPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));

        tblViewCurso.setItems(cursosFiltrados);
    }

    private void carregarCursosDoBanco() {
        try {
            cursos.clear();
            cursos.addAll(cursoDAO.listarTodos());
        } catch (SQLException e) {
            System.err.println("Erro ao carregar cursos: " + e.getMessage());
        }
    }

    @FXML
    private void abrirPopupCurso() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PopupCurso.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Adicionar Curso");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();

        PopupCursoController popupCtrl = loader.getController();

        if (popupCtrl.isConfirmado()) {
            String coordenadorSelecionado = popupCtrl.getCoordenadorSelecionado();

            // Verifica se o coordenador já está atribuído a algum curso
            boolean coordenadorJaUtilizado = cursos.stream()
                    .anyMatch(c -> c.getCoordenador().equalsIgnoreCase(coordenadorSelecionado.trim()));

            if (coordenadorJaUtilizado) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Coordenador já atribuído");
                alerta.setHeaderText(null);
                alerta.setContentText("O coordenador '" + coordenadorSelecionado + "' já está vinculado a outro curso.");
                alerta.showAndWait();
                return;
            }

            Curso novo = new Curso(
                    popupCtrl.getCursoSelecionado(),
                    coordenadorSelecionado,
                    popupCtrl.getPeriodoSelecionado()
            );
            cursoDAO.criar(novo);
            carregarCursosDoBanco();
        }
    }

    @FXML
    private void abrirPopupFiltro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PopupFiltro.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Filtrar Cursos");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();

        PopupFiltroController popFiltro = loader.getController();
        if (popFiltro.isConfirmado()) {
            if (popFiltro.isLimparFiltro()) {
                filtroAtual = "";
                cursosFiltrados.setPredicate(c -> true);
            } else {
                aplicarFiltro(popFiltro.getCoordenadorSelecionado());
            }
        }
    }

    private void aplicarFiltro(String coordenador) {
        filtroAtual = coordenador.toLowerCase();
        cursosFiltrados.setPredicate(c ->
                filtroAtual.isEmpty() ||
                        c.getCoordenador().toLowerCase().contains(filtroAtual)
        );
    }

    @FXML
    private void deletarCursosSelecionados() {
        boolean algumSelecionado = cursos.stream().anyMatch(Curso::isSelecionado);

        if (!algumSelecionado) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecione ao menos um curso para deletar.");
            alerta.showAndWait();
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("Deletar curso(s)");
        confirmacao.setContentText("Tem certeza que deseja excluir os cursos selecionados?");
        confirmacao.initModality(Modality.APPLICATION_MODAL);

        confirmacao.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                List<Curso> cursosParaDeletar = cursos.stream()
                        .filter(Curso::isSelecionado)
                        .collect(Collectors.toList());

                for (Curso curso : cursosParaDeletar) {
                    cursoDAO.delete(curso.getId());
                }

                carregarCursosDoBanco();
            }
        });
    }

    @FXML
    private void mouseEntrou(MouseEvent e) {
        ((Region) e.getSource()).setStyle("-fx-background-color: #eaf2ff;");
    }

    @FXML
    private void mouseSaiu(MouseEvent e) {
        ((Region) e.getSource()).setStyle("-fx-background-color: transparent;");
    }
}
