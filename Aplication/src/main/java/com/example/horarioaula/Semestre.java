package com.example.horarioaula;

public class Semestre {

    private String id;
    private String nome;

    // Construtor - ID recebe o valor passado como parâmetro
    public Semestre(String id, String nome){
        this.id = id;
        this.nome = nome;
    }

    // Permite alterar o valor de id
    public void setId(String novoId) {
        this.id = novoId;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    // Retorna o valor de id
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


}
