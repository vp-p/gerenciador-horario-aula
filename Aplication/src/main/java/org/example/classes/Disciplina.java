
package org.example.classes;

public class Disciplina {
    private int id_disciplina;
    private String nome;
    private int id_professor;
    private int id_curso;
    private int semestre;

    public Disciplina(int id_disciplina, String nome, int id_professor, int id_curso, int semestre) {
        this.id_disciplina = id_disciplina;
        this.nome = nome;
        this.id_professor = id_professor;
        this.id_curso = id_curso;
        this.semestre = semestre;
        System.out.println("id: "+ this.id_disciplina);
    }

    public int getId() {
        return id_disciplina;
    }

    public void setId(int id) {
        this.id_disciplina = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
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
