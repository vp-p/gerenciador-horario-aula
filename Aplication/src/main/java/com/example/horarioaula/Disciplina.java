package com.example.horarioaula;

public class Disciplina {
    private String id;
    private String nome;
    private String curso;
    private String semestre;

    // Construtor
    public Disciplina(String id, String nome, String curso, String semestre) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.semestre = semestre;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}

