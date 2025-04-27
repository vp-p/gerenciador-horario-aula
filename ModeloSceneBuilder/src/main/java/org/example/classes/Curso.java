package org.example.classes;

public class Curso {
    private int id;
    private String nome;
    private String coordenador;

    public Curso(int id, String nome, String coordenador) {
        this.id = id;
        this.nome = nome;
        this.coordenador = coordenador;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome; // é isso que será exibido no ComboBox
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public void setId(long id) {
        this.id = (int) id;
    }

}
