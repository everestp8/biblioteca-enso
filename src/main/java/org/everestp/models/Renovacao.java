package org.everestp.models;

import java.time.LocalDate;

public class Renovacao implements Model {
    private int id, emprestimoFk, usuarioFk;
    private LocalDate dtRenovacao;

    public Renovacao(int id, int emprestimoFk, int usuarioFk, LocalDate dtRenovacao) {
        this.id = id;
        this.emprestimoFk = emprestimoFk;
        this.usuarioFk = usuarioFk;
        this.dtRenovacao = dtRenovacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
