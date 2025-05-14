package org.example.controller;

// Importações JavaFX para controles visuais
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

// Importações do projeto (DAO e modelo)
import org.example.dao.ProfessorDAO;
import org.example.classes.Professor;

import java.util.List;

// Controlador da janela popup de filtro por coordenador
public class PopupFiltroController {

    // Elementos do FXML (botões e combo box)
    @FXML private Button btnAplicar;
    @FXML private Button btnLimpar;
    @FXML private ComboBox<String> comboCoordenador;

    // Variáveis de controle do estado do popup
    private boolean confirmado = false;        // se o botão "Aplicar" ou "Limpar" foi pressionado
    private boolean limparFiltro = false;      // indica se é para limpar o filtro
    private String coordenadorSelecionado;     // nome do coordenador selecionado

    // Inicializa a ComboBox com a lista de professores (coordenadores)
    @FXML
    private void initialize() {
        try {
            // Busca todos os professores do banco de dados
            List<Professor> profs = new ProfessorDAO().buscarProfessores();

            // Adiciona os nomes na ComboBox
            profs.forEach(p -> comboCoordenador.getItems().add(p.getNomeProfessor()));
        } catch (Exception e) {
            // Se der erro, exibe um alerta
            exibirErro("Erro ao carregar coordenadores: " + e.getMessage());
        }
    }

    // Botão "Aplicar": aplica o filtro com o coordenador selecionado
    @FXML
    private void Confirmar() {
        coordenadorSelecionado = comboCoordenador.getValue();

        // Valida se o usuário selecionou um coordenador
        if (coordenadorSelecionado == null) {
            exibirErro("Selecione um coordenador para filtrar.");
            return;
        }

        confirmado = true; // confirma que o botão foi pressionado
        fechar();          // fecha o popup
    }

    // Botão "Limpar": limpa o filtro atual
    @FXML
    private void Limpar() {
        limparFiltro = true; // define que o usuário quer limpar o filtro
        confirmado   = true; // também é uma confirmação
        fechar();            // fecha o popup
    }

    // Exibe uma janela de erro com a mensagem passada
    private void exibirErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // ajusta altura do alerta
        alert.setContentText(msg);
        alert.showAndWait(); // espera o usuário fechar o alerta
    }

    // Fecha a janela do popup
    private void fechar() {
        Stage stage = (Stage) btnAplicar.getScene().getWindow(); // pega a janela atual
        stage.close(); // fecha
    }

    // Getters para acessar o estado do popup
    public boolean isConfirmado(){
        return confirmado;
    }

    public boolean isLimparFiltro(){
        return limparFiltro;
    }

    public String getCoordenadorSelecionado(){
        return coordenadorSelecionado;
    }
}
