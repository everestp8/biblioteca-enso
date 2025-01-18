package org.everestp.daos;

import org.everestp.models.Renovacao;

import java.util.ArrayList;
import java.util.List;

public class RenovacaoDAO extends InMemoryDAO<Renovacao> {
    public List<Renovacao> getByUsuarioIdAndEmprestimo(int usuarioId, int emprestimoId) {
        List<Renovacao> renovacoes = new ArrayList<>();
        for (Renovacao r : this.getAll())
            if (r.getUsuarioFk() == usuarioId && r.getEmprestimoFk() == emprestimoId)
                renovacoes.add(r);
        return  renovacoes;
    }
}
