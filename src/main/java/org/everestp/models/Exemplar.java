package org.everestp.models;

public class Exemplar implements Model {
    private final int id, livroFk;
    private final String idFisico;
    private final Boolean disponivel;

    public Exemplar(int id, int livroFk, String idFisico, Boolean disponivel) {
        this.id = id;
        this.livroFk = livroFk;
        this.idFisico = idFisico;
        this.disponivel = disponivel;
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
    public boolean getDisponivel() {
        return disponivel;
    }
}
