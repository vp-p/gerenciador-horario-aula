package org.example.classes;

public class Professor {

    private String nomeProfessor;
    private String emailProfessor;
    private int id;

    public String toString() {
        return this.nomeProfessor;
    }
    public Professor(int id, String nome, String email) {
        this.id = id;
        this.nomeProfessor = nome;
        this.emailProfessor =  email;
    }

    public int getId() {
        return id;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }
}

