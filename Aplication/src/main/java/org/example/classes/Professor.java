package org.example.classes;


public class Professor {

    private String nomeProfessor;
    private String emailProfessor;
    private Integer id;

    public String toString() {
        return this.nomeProfessor;
    }

    public Professor(Integer id,String nomeProfessor, String emailProfessor){
        this.id = id;
        this.nomeProfessor = nomeProfessor;
        this.emailProfessor = emailProfessor;
    }


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

