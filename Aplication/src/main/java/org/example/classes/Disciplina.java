
package org.example.classes;

import com.mysql.cj.conf.IntegerProperty;

import java.util.UUID;

public class Disciplina {;
    private Integer id;
    private String nome;
    private Integer professor;
    private Integer curso;
    private int semestre;
    private int cargaHoraria;

    public Disciplina(String nome, Professor professor, Curso curso, int semestre, int cargaHoraria) {
        this.nome = nome;
        this.professor = professor.getId();
        this.curso = curso.getId();
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
    }

    public Disciplina(Integer id, String nome, Professor professor, Curso curso, int semestre, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.professor = professor.getId();
        this.curso = curso.getId();
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer getProfessor() {
        return professor;
    }

    public void setProfessor(Integer professor) {
        this.professor = professor;
    }

    public Integer getCurso() {
        return curso;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void getCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
}
