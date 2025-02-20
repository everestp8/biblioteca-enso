package org.everestp.services;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.RenovacaoDAO;
import org.everestp.exceptions.DadosInvalidosException;
import org.everestp.exceptions.MaximoDeRenovacoesAtingidoException;
import org.everestp.models.Emprestimo;
import org.everestp.models.Renovacao;
import org.everestp.utils.Validator;

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

    public void renovarEmprestimo(String idFisico, int usuarioId) {
        if (!Validator.validarIdFisico(idFisico))
            throw new DadosInvalidosException("ID físico inválido.");
        Emprestimo emprestimo = this.emprestimoDAO.getAtivoByExemplarIdFisico(idFisico);
        int quantRenovacoes = this.renovacaoDAO.getByUsuarioIdAndEmprestimo(usuarioId, emprestimo.getId()).size();
        if (quantRenovacoes > 3)
            throw new MaximoDeRenovacoesAtingidoException();
        this.emprestimoDAO.setDtPrazo(emprestimo.getId(), emprestimo.getDtPrazo().plusDays(7));
        Renovacao renovacao = new Renovacao(0, emprestimo.getId(), usuarioId, LocalDate.now());
        this.renovacaoDAO.save(renovacao);
    }

    public int countRenovacoes() {
        return this.renovacaoDAO.countAll();
    }
}
