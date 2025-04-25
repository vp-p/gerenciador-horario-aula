package org.example.classes;

public class Disciplina {
    private Long id;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private Curso curso;

    public Disciplina(String nome, int cargaHoraria, Professor professor, Curso curso) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor idProfessor) {
        this.professor = professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso idCurso) {
        this.curso = curso;
    }
}
