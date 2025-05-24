
package org.example.classes;

public class Disciplina {
    ;
    private Integer id;
    private String nome;
    private Integer idProfessor;
    private Integer idCurso;
    private Integer semestre;


    public Disciplina(String nome, Integer idProfessor, Integer idCurso, int semestre) {
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.idCurso = idCurso;
        this.semestre = semestre;
    }

    public Disciplina(Integer id, String nome, Integer idProfessor, Integer idCurso, int semestre) {
        this.id = id;
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.idCurso = idCurso;
        this.semestre = semestre;
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


    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer professor) {
        this.idProfessor = idProfessor;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer curso) {
        this.idCurso = idCurso;
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
}
