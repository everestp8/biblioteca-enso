package org.everestp.models;

import java.time.LocalDate;

public class Emprestimo implements Model {
    private final int id, exemplarFk, usuarioFk;
    private final LocalDate dtEmprestimo, dtDevolucao, dtPrazo;

    public Emprestimo(int id, int exemplarFk, int usuarioFk, LocalDate dtEmprestimo, LocalDate dtDevolucao, LocalDate dtPrazo) {
        this.id = id;
        this.exemplarFk = exemplarFk;
        this.usuarioFk = usuarioFk;
        this.dtEmprestimo = dtEmprestimo;
        this.dtDevolucao = dtDevolucao;
        this.dtPrazo = dtPrazo;
    }

    public int getId() {
        return id;
    }

    public int getExemplarFk() {
        return exemplarFk;
    }

    public int getUsuarioFk() {
        return usuarioFk;
    }

    public LocalDate getDtEmprestimo() {
        return dtEmprestimo;
    }

    public LocalDate getDtDevolucao() {
        return dtDevolucao;
    }

    public LocalDate getDtPrazo() {
        return dtPrazo;
    }
}
