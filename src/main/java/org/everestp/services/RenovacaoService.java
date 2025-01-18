package org.everestp.services;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.RenovacaoDAO;
import org.everestp.models.Emprestimo;
import org.everestp.models.Renovacao;

import java.time.LocalDate;
import java.util.List;

public class RenovacaoService {
    private final RenovacaoDAO renovacaoDAO;
    private final EmprestimoDAO emprestimoDAO;

    public RenovacaoService(RenovacaoDAO renovacaoDAO, EmprestimoDAO emprestimoDAO) {
        this.renovacaoDAO = renovacaoDAO;
        this.emprestimoDAO = emprestimoDAO;
    }

    public List<Renovacao> getRenovacoesByUsuarioIdAndEmprestimoId(int usuarioId, int emprestimoId) {
        return this.renovacaoDAO.getByUsuarioIdAndEmprestimo(usuarioId, emprestimoId);
    }

    public int renovarEmprestimo(int emprestimoId, int usuarioId) {
        Emprestimo emprestimo = this.emprestimoDAO.getById(emprestimoId);
        int quantRenovacoes = this.renovacaoDAO.getByUsuarioIdAndEmprestimo(usuarioId, emprestimoId).size();
        if (quantRenovacoes > 3)
            return 2;
        try {
            this.emprestimoDAO.setDtPrazo(emprestimoId, emprestimo.getDtPrazo().plusDays(7));
        } catch (Exception e) {
            return 1;
        }
        Renovacao renovacao = new Renovacao(0, emprestimoId, usuarioId, LocalDate.now());
        this.renovacaoDAO.save(renovacao);
        return 0;
    }
}
