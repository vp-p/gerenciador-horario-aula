package org.example.classes;

public class Aula {
    private int idAula;
    private int idProfessor;
    private int idDisciplina;
    private int idCurso;
    private String diaSemana;
    private int numeroAula;
    private String nomeDisciplina;
    private String periodo;
    private boolean deletado; //

    public Aula(int idAula, int idProfessor, int idDisciplina, int idCurso, String diaSemana, int numeroAula, String nomeDisciplina, String periodo) {
        this.idAula = idAula;
        this.idProfessor = idProfessor;
        this.idDisciplina = idDisciplina;
        this.idCurso = idCurso;
        this.diaSemana = diaSemana;
        this.numeroAula = numeroAula;
        this.nomeDisciplina = nomeDisciplina;
        this.periodo = periodo;
    }

    public int getIdAula() {
        return idAula;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public int getNumeroAula() {
        return numeroAula;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public String getPeriodo() {
        return periodo;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
}