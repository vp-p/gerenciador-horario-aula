package org.example.classes;

public class Professor {

    private String nomeProfessor;
    private String emailProfessor;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }
}

