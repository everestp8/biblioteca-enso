package org.everestp.models;

public class Usuario implements Model {

    private int id, papel;
    private String email, senha, cpf;

    public Usuario(int id, String email, String senha, String cpf, int papel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.papel = papel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }

    public int getPapel() {
        return papel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
