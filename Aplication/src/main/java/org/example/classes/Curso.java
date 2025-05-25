package org.example.classes;

import javafx.beans.property.*;

public class Curso {
    private final BooleanProperty selecionado;
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty coordenador;
    private final StringProperty periodo;
    private boolean deletado;

    // Construtor completo
    public Curso(boolean selecionado, long id, String nome, String coordenador, String periodo, boolean deletado) {
        this.selecionado = new SimpleBooleanProperty(selecionado);
        this.id = new SimpleIntegerProperty((int) id);
        this.nome = new SimpleStringProperty(nome);
        this.coordenador = new SimpleStringProperty(coordenador);
        this.periodo = new SimpleStringProperty(periodo);
        this.deletado = deletado;
    }

    // Construtor para novo curso
    public Curso(String nome, String coordenador, String periodo) {
        this(false, 0L, nome, coordenador, periodo, false);
    }



//Construtor para listar cursos no disciplina controller (teste)************************************************
    public Curso( int idCurso, String nome, String coordenador, String periodo) {
        this(false, idCurso, nome, coordenador, periodo, false);
    }

    // Getters e setters

    public boolean isSelecionado() {
        return selecionado.get();
    }

    public void setSelecionado(boolean sel) {
        selecionado.set(sel);
    }

    public BooleanProperty selecionadoProperty() {
        return selecionado;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getCoordenador() {
        return coordenador.get();
    }

    public void setCoordenador(String coordenador) {
        this.coordenador.set(coordenador);
    }

    public StringProperty coordenadorProperty() {
        return coordenador;
    }

    public String getPeriodo() {
        return periodo.get();
    }

    public void setPeriodo(String periodo) {
        this.periodo.set(periodo);
    }

    public StringProperty periodoProperty() {
        return periodo;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }


    //adicionado toString para exibir o nome do curso na combobox
    public String toString() {
        return this.nome.get();
    }
}