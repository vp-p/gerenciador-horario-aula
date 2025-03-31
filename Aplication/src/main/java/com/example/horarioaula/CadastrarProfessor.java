package com.example.horarioaula;

public class CadastrarProfessor {

    private String nome;
    private String email;
    private String curso;

    // Construtor - Nome recebe o valor passado como parâmetro

    public CadastrarProfessor(String nome, String email, String curso) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    // Permite alterar o valor de nome

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    // Permite alterar o valor de email

    public void setEmail(String novoEmail) { this.email = novoEmail; }

    // Permite alterar o valor de curso

    public void setCurso(String novoCurso) { this.curso = novoCurso; }

    // Retorna o valor de nome

    public String getNome() { return nome; }

    // Retorna o valor de email

    public String getEmail() { return email; }

    // Retorna o valor de curso

    public String getCurso() { return curso; }

}
