package org.example.classes;

public class Aula {
    private int idAula;
    private int idProfessor;
    private int idDisciplina;
    private int idCurso;
    private String diaSemana;
    private int numeroAula;
    private String nomeDisciplina;
    private String Periodo;


    // Construtor completo
    public Aula(int idAula, int idProfessor, int idDisciplina, int idCurso, String diaSemana, int numeroAula, String nomeDisciplina, String periodo) {
        this.idAula = idAula;
        this.idProfessor = idProfessor;
        this.idDisciplina = idDisciplina;
        this.idCurso = idCurso;
        this.diaSemana = diaSemana;
        this.numeroAula = numeroAula;
        this.nomeDisciplina = nomeDisciplina;
        this.Periodo    =   periodo;
    }

    // Getters e Setters

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(int numeroAula) {
        this.numeroAula = numeroAula;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "idAula=" + idAula +
                ", idProfessor=" + idProfessor +
                ", idDisciplina=" + idDisciplina +
                ", idCurso=" + idCurso +
                ", diaSemana=" + diaSemana +
                ", numeroAula=" + numeroAula +
                '}';
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }
}
