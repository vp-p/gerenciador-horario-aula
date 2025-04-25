package org.example.classes;


import java.time.LocalTime;
import java.util.UUID;

public class Aula {
    private Long id;
    private Professor idProfessor;
    private Disciplina idDisciplina;
    private Curso idCurso;
    private LocalTime inicioAula;
    private LocalTime fimAula;

    public Aula(Professor idProfessor, Disciplina idDisciplina, Curso idCurso, LocalTime inicioAula, LocalTime fimAula) {
        this.idProfessor = idProfessor;
        this.idDisciplina = idDisciplina;
        this.idCurso = idCurso;
        this.inicioAula = inicioAula;
        this.fimAula = fimAula;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professor getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Professor idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Disciplina getIdDisciplina() {
        return idDisciplina;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public LocalTime getInicioAula() {
        return inicioAula;
    }

    public void setInicioAula(LocalTime inicioAula) {
        this.inicioAula = inicioAula;
    }

    public LocalTime getFimAula() {
        return fimAula;
    }

    public void setFimAula(LocalTime fimAula) {
        this.fimAula = fimAula;
    }

}
