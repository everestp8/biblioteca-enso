package org.everestp.services;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.EmprestimoDTO;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.models.Usuario;

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

    // TODO: Adicionar validação de punição antes de permitir o empréstimo (Sim ela deve ser feita aqui [ou não?])
    public int fazerEmprestimo(EmprestimoDTO dadosEmprestimo) {
        Exemplar exemplar = this.exemplarDAO.getByIdFisico(dadosEmprestimo.exemplarIdFIsico());
        if (exemplar == null)
            return 1;

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

    public List<Emprestimo> getAllEmprestimosByUsuarioId(int usuarioId) {
        return this.emprestimoDAO.getByUsuarioFk(usuarioId);
    }
}
