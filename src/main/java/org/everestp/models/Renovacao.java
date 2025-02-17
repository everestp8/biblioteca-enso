package org.everestp.models;

import java.time.LocalDate;

public class Renovacao implements Model {
    private final int id, emprestimoFk, usuarioFk;
    private final LocalDate dtRenovacao;

    public Renovacao(int id, int emprestimoFk, int usuarioFk, LocalDate dtRenovacao) {
        this.id = id;
        this.emprestimoFk = emprestimoFk;
        this.usuarioFk = usuarioFk;
        this.dtRenovacao = dtRenovacao;
    }

    public int getId() {
        return id;
    }

    public int getEmprestimoFk() {
        return emprestimoFk;
    }

    public int getUsuarioFk() {
        return usuarioFk;
    }

    public LocalDate getDtRenovacao() {
        return dtRenovacao;
    }
}
