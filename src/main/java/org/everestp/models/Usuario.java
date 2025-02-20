package org.everestp.models;

public class Usuario implements Model {

    private final int id;
    private final String nome, email, senha, cpf;
    private final Integer papel;

    public Usuario(int id, String nome, String email, String senha, String cpf, Integer papel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.papel = papel;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getPapel() {
        return papel;
    }

}
