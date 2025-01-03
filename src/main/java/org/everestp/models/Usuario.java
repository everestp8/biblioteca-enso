package org.everestp.models;

public class Usuario implements Model {

    private int id;
    private String email, senha, cpf;
    private Integer papel;

    public Usuario(int id, String email, String senha, String cpf, int papel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.papel = papel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
