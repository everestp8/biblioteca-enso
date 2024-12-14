package org.everestp.models;

public class Exemplar implements Model {
    private int id, livroFk;
    private String idFisico;

    public Exemplar(int id, int livroFk, String idFisico) {
        this.id = id;
        this.livroFk = livroFk;
        this.idFisico = idFisico;
    }

    public int getId() {
        return id;
    }

    public int getLivroFk() {
        return livroFk;
    }

    public String getIdFisico() {
        return idFisico;
    }
}
