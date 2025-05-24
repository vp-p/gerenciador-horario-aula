package org.example.classes;

import javafx.beans.property.*;

public class Curso {
    private final BooleanProperty selecionado;
    private final IntegerProperty id;
    private String  nome;
    private final StringProperty coordenador;
    private String periodo;
    private boolean deletado;

    // Construtor completo
    public Curso(boolean selecionado, long id, String nome, String coordenador, String periodo, boolean deletado) {
        this.selecionado = new SimpleBooleanProperty(selecionado);
        this.id = new SimpleIntegerProperty((int) id);
        this.nome = new String(nome);
        this.coordenador = new SimpleStringProperty(coordenador);
        this.periodo = new String(periodo);
        this.deletado = deletado;
    }

    @Override
    public String toString() {
        return nome +" -"+periodo; // é isso que será exibido no ComboBox
    }

    // Construtor para novo curso
    public Curso(String nome, String coordenador, String periodo) {
        this(false, 0L, nome, coordenador, periodo, false);
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
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String nome() {
        return this.nome;
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
        return this.periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo=periodo;
    }

    public String periodoProperty() {
        return periodo;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}