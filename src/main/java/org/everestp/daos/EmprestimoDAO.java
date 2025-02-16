package org.everestp.daos;

import org.everestp.models.Emprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO extends InMemoryDAO<Emprestimo> {
    public List<Emprestimo> getByUsuarioFk(int usuarioId) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        for (Emprestimo e : this.getAll())
            if (e.getUsuarioFk() == usuarioId)
                emprestimos.add(e);
        return emprestimos;
    }

    public Emprestimo getAtivoByExemplarIdFisico(int usuarioId, String idFIsico) {
        for (Emprestimo e : this.getByUsuarioFk(usuarioId)) {
            if (e.getDtDevolucao() != null)
                continue;
        }
        return null;
    }

    public Emprestimo getByExemplarIdFisico(int usuarioId, String idFisico) {
        return null;
    }

    public void setDtPrazo(int emprestimoId, LocalDate novaDtPrazo) {
        Emprestimo e = this.getById(emprestimoId);
        Emprestimo novoEmprestimo = new Emprestimo(e.getId(), e.getExemplarFk(), e.getUsuarioFk(), e.getDtEmprestimo(), e.getDtDevolucao(), novaDtPrazo);
        this.update(novoEmprestimo);
    }

    public void setDtDevolucao(int emprestimoId, LocalDate novaDtDevolucao) {
        Emprestimo e = this.getById(emprestimoId);
        Emprestimo novoEmprestimo = new Emprestimo(e.getId(), e.getExemplarFk(), e.getUsuarioFk(), e.getDtEmprestimo(), novaDtDevolucao, e.getDtPrazo());
        this.update(novoEmprestimo);
    }
}
