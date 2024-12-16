package org.everestp.models;

import java.time.LocalDate;

public class Emprestimo implements Model {
    private int id, exemplarFk;
    private String cpfFk;
    private LocalDate dtEmprestimo, dtDevolucao, dtPrazo;

    public Emprestimo(int id, int exemplarFk, String cpfFk, LocalDate dtEmprestimo, LocalDate dtDevolucao, LocalDate dtPrazo) {
        this.id = id;
        this.exemplarFk = exemplarFk;
        this.cpfFk = cpfFk;
        this.dtEmprestimo = dtEmprestimo;
        this.dtDevolucao = dtDevolucao;
        this.dtPrazo = dtPrazo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExemplarFk() {
        return exemplarFk;
    }

    public String getCpfFk() {
        return cpfFk;
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
