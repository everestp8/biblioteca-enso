package org.everestp.views;

import org.everestp.daos.EmprestimoDAO;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.EmprestimoDTO;
import org.everestp.services.EmprestimoService;

import java.util.Scanner;

public class EmprestimoView {
    private final Scanner scan;
    private final Scanner scanLines;
    private final EmprestimoService emprestimoService;

    public EmprestimoView(EmprestimoDAO emprestimoDAO, ExemplarDAO exemplarDAO, UsuarioDAO usuarioDAO) {
        this.scan = new Scanner(System.in);
        this.scanLines = new Scanner(System.in);
        this.emprestimoService = new EmprestimoService(emprestimoDAO, exemplarDAO, usuarioDAO);
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
