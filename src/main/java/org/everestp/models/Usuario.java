package org.everestp.models;

public class Usuario implements Model {

    private int id, papel;
    private String email, senha, cpf;
    private boolean ativo;

    public Usuario(int id, String email, String senha, String cpf, int papel, boolean ativo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.papel = papel;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPapel() {
        return papel;
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
