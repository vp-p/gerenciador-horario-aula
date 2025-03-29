package com.example.horarioaula;

public class CadastrarProfessor {

    private String nome;
    private String email;

    // Construtor - Nome recebe o valor passado como parâmetro

    public CadastrarProfessor(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    // Permite alterar o valor de nome

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    // Permite alterar o valor de email

    public void setEmail(String novoEmail) { this.email = novoEmail; }

    // Retorna o valor de nome

    public String getNome() { return nome; }

    // Retorna o valor de email

    public String getEmail() { return email; }

}
