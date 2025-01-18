package org.everestp.services;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.RenovacaoDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.EmprestimoDTO;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.models.Renovacao;
import org.everestp.models.Usuario;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {
    private final EmprestimoDAO emprestimoDAO;
    private final ExemplarDAO exemplarDAO;
    private final UsuarioDAO usuarioDAO;
    private final RenovacaoDAO renovacaoDAO;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, ExemplarDAO exemplarDAO, UsuarioDAO usuarioDAO, RenovacaoDAO renovacaoDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.exemplarDAO = exemplarDAO;
        this.usuarioDAO = usuarioDAO;
        this.renovacaoDAO = renovacaoDAO;
    }

    // TODO: Adicionar validação de punição antes de permitir o empréstimo (Sim ela deve ser feita aqui [ou não?])
    public int fazerEmprestimo(EmprestimoDTO dadosEmprestimo) {
        Exemplar exemplar = this.exemplarDAO.getByIdFisico(dadosEmprestimo.exemplarIdFIsico());
        if (exemplar == null)
            return 1;
        if (!exemplar.getDisponivel())
            return 2;

        Usuario usuario = this.usuarioDAO.getByCpf(dadosEmprestimo.cpfUsuario());
        if (usuario == null)
            return 1;

        LocalDate dtEmprestimo = LocalDate.now();
        LocalDate dtPrazo = LocalDate
                .now()
                .plusDays(7);

        Emprestimo novoEmprestimo = new Emprestimo(0, exemplar.getId(), usuario.getId(), dtEmprestimo, null, dtPrazo);
        this.emprestimoDAO.save(novoEmprestimo);

        this.exemplarDAO.setDisponibilidadeById(exemplar.getId(), false);

        return 0;
    }

    public int deletarEmprestimo(Emprestimo emprestimo) {
        try {
            this.emprestimoDAO.delete(emprestimo.getId());
            this.exemplarDAO.setDisponibilidadeById(emprestimo.getExemplarFk(), true);
            List<Renovacao> renovacaos = this.renovacaoDAO.getByUsuarioIdAndEmprestimo(emprestimo.getUsuarioFk(), emprestimo.getId());
            for (Renovacao r : renovacaos)
                this.renovacaoDAO.delete(r.getId());
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }

    public Emprestimo getEemprestimoPendenteByExemplarId(int usuarioId, int exemplarId) {
        return this.emprestimoDAO.getPendenteByExemplarFk(usuarioId, exemplarId);
    }

    public List<Emprestimo> getAllEmprestimosByUsuarioId(int usuarioId) {
        return this.emprestimoDAO.getByUsuarioFk(usuarioId);
    }
}
