package org.example.classes;

import java.util.UUID;

public class Curso {
    private Long id;
    private String nome;
    private String coordenador;

    public Curso(String nome, String coordenador) {
        this.nome = nome;
        this.coordenador = coordenador;
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

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }
}
