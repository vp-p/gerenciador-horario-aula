package com.example.horarioaula;

public class Curso {

    private String id;
    private String nome;
    private String periodo;

    // Construtor - ID recebe o valor passado como parâmetro
    public Curso(String id, String nome, String periodo){
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
    }

    // Permite alterar o valor de id
    public void setId(String novoId) {
        this.id = novoId;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setPeriodo(String novoPeriodo){
        this.periodo = novoPeriodo;
    }

    // Retorna o valor de id
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPeriodo() {
        return periodo;
    }

}
