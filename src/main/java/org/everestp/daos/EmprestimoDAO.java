package org.everestp.daos;

import org.everestp.models.Emprestimo;

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

    public Emprestimo getPendenteByExemplarFk(int usuarioId, int exemplarId) {
        for (Emprestimo e : this.getByUsuarioFk(usuarioId)) {
            if (e.getDtDevolucao() != null)
                continue;
            if (e.getExemplarFk() == exemplarId)
                return e;
        }
        return null;
    }
}
