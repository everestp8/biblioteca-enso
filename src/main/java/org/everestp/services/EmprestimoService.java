package org.everestp.services;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.EmprestimoDTO;
import org.everestp.exceptions.*;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.models.Usuario;
import org.everestp.utils.Validator;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {
    private final EmprestimoDAO emprestimoDAO;
    private final ExemplarDAO exemplarDAO;
    private final UsuarioDAO usuarioDAO;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, ExemplarDAO exemplarDAO, UsuarioDAO usuarioDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.exemplarDAO = exemplarDAO;
        this.usuarioDAO = usuarioDAO;
    }

    // TODO: Adicionar validação de punição antes de permitir o empréstimo
    public void fazerEmprestimo(EmprestimoDTO dadosEmprestimo) {
        Exemplar exemplar = this.exemplarDAO.getByIdFisico(dadosEmprestimo.exemplarIdFIsico());
        if (!Validator.validarEmprestimoDTO(dadosEmprestimo))
            throw new DadosInvalidosException("Dados inválidos para empréstimo.");
        if (exemplar == null)
            throw new ExemplarNaoEncontradoException();
        if (!exemplar.getDisponivel())
            throw new ExemplarNaoDisponivelException();

        Usuario usuario = this.usuarioDAO.getByCpf(dadosEmprestimo.cpfUsuario());
        if (usuario == null)
            throw new UsuarioNaoEncontradoException();

        LocalDate dtEmprestimo = LocalDate.now();
        LocalDate dtPrazo = LocalDate
                .now()
                .plusDays(7);

        Emprestimo novoEmprestimo = new Emprestimo(0, exemplar.getId(), usuario.getId(), dtEmprestimo, null, dtPrazo);
        this.emprestimoDAO.save(novoEmprestimo);

        this.exemplarDAO.setDisponibilidadeById(exemplar.getId(), false);
    }

    public void devolverEmprestimo(String idFisico) {
        if (!Validator.validarIdFisico(idFisico))
            throw new DadosInvalidosException("ID físico inválido.");
        Emprestimo emprestimo = this.emprestimoDAO.getAtivoByExemplarIdFisico(idFisico);
        if (emprestimo == null)
            throw new EmprestimoNaoEncontradoException();
        this.emprestimoDAO.setDtDevolucao(emprestimo.getId(), LocalDate.now());
        this.exemplarDAO.setDisponibilidadeById(emprestimo.getExemplarFk(), true);
    }

    public List<Emprestimo> getAllEmprestimosByUsuarioId(int usuarioId) {
        List<Emprestimo> emprestimos = this.emprestimoDAO.getByUsuarioFk(usuarioId);
        if (emprestimos == null)
            throw new EmprestimoNaoEncontradoException();
        return emprestimos;
    }

    public List<Emprestimo> getAllEmprestimosBetweenDates(LocalDate dataInicio, LocalDate dataFim) {
        List<Emprestimo> emprestimos = this.emprestimoDAO.getBetweenDates(dataInicio, dataFim);
        if (emprestimos == null)
            throw new EmprestimoNaoEncontradoException();
        return emprestimos;
    }

    public int countEmprestimos() {
        return this.emprestimoDAO.countAll();
    }
}
