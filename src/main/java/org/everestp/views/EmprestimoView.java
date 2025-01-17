package org.everestp.views;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.EmprestimoDTO;
import org.everestp.models.Emprestimo;
import org.everestp.models.Exemplar;
import org.everestp.models.Livro;
import org.everestp.services.EmprestimoService;
import org.everestp.services.ExemplarService;
import org.everestp.services.LivroService;

import java.util.List;
import java.util.Scanner;

public class EmprestimoView {
    private final Scanner scan;
    private final Scanner scanLines;
    private final EmprestimoService emprestimoService;
    private final ExemplarService exemplarService;
    private final LivroService livroService;

    public EmprestimoView(EmprestimoDAO emprestimoDAO, ExemplarDAO exemplarDAO, UsuarioDAO usuarioDAO, LivroDAO livroDAO) {
        this.scan = new Scanner(System.in);
        this.scanLines = new Scanner(System.in);
        this.emprestimoService = new EmprestimoService(emprestimoDAO, exemplarDAO, usuarioDAO);
        this.exemplarService = new ExemplarService(exemplarDAO, livroDAO);
        this.livroService = new LivroService(livroDAO);
    }

    public void listarEmprestimosUsuario(int usuarioId) {
        System.out.println("# Seus empréstimos: ");
        List<Emprestimo> emprestimos = this.emprestimoService.getAllEmprestimosByUsuarioId(usuarioId);
        if (emprestimos == null) {
            System.out.println("Não foi possível listar os empréstimos do usuário.");
            return;
        }
        for (Emprestimo e : emprestimos) {
            Livro livro;
            Exemplar exemplar;
            exemplar = this.exemplarService.getExemplarById(e.getExemplarFk());
            livro = this.livroService.getLivroById(exemplar.getLivroFk());
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Exemplar: " + exemplar.getIdFisico());
            System.out.printf("Data de empréstimo: " + e.getDtEmprestimo().toString());
            System.out.printf("Prazo de entrega: " + e.getDtPrazo().toString());
            if (e.getDtDevolucao() != null) {
                System.out.printf("Data de devolução: " + e.getDtDevolucao().toString());
            } else {
                System.out.println("Devolução pendente.");
            }

        }
    }

    public void novoEmprestimo() {
        System.out.println("# Fazer empréstimo: ");
        System.out.println("Digite o Id físico do livro a ser emprestado: ");
        String idFisico = scan.next();
        System.out.println("Digite o CPF do usuário a receber o livro: ");
        String cpfUsuario = scan.next();

        EmprestimoDTO dadosEmprestimo = new EmprestimoDTO(idFisico, cpfUsuario);
        int code = this.emprestimoService.fazerEmprestimo(dadosEmprestimo);
        if (code != 0) {
            System.out.println("Não foi possível concluir o empréstimo.");
            return;
        }

        System.out.println("Empréstimo concluído com sucesso!");
    }
}
