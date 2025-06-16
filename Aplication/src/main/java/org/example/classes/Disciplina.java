
package org.example.classes;

public class Disciplina {
    private Integer id;
    private String nome;
    private Integer idProfessor;
    private Integer idCurso;
    private Integer semestre;
    private String nomeProfessor;
    private String nomeCurso;

    public Disciplina(String nome, Integer idProfessor, Integer idCurso, Integer semestre) {
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.idCurso = idCurso;
        this.semestre = semestre;
    }

    public Disciplina(Integer id, String nome, Integer idProfessor, Integer idCurso, Integer semestre,
                      String nomeProfessor, String nomeCurso) {
        this.id = id;
        this.nome = nome;
        this.idProfessor = idProfessor;
        this.idCurso = idCurso;
        this.semestre = semestre;
        this.nomeProfessor = nomeProfessor;
        this.nomeCurso = nomeCurso;
    }


    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
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

//    public String getNomeProfessor() {
//        return id_professor != null ? new Professor(id_professor, "", "").toString() : "Sem Professor";
//    }

//    public void setId_professor(Integer professor) {
//        this.id_professor = id_professor;
//    }

    public Integer getIdCurso() {
        return idCurso;
    }

//    public void setIdCurso(Integer curso) {
//        this.nomeCurso = nomeCurso;
//    }

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