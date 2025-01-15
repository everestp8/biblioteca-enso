package org.everestp.models;

public class Exemplar implements Model {
    private int id, livroFk;
    private String idFisico;
    private Boolean disponivel;

    public Exemplar(int id, int livroFk, String idFisico, Boolean disponivel) {
        this.id = id;
        this.livroFk = livroFk;
        this.idFisico = idFisico;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroFk() {
        return livroFk;
    }

    public String getIdFisico() {
        return idFisico;
    }
    public boolean getDisponivel() {
        return disponivel;
    }
}
