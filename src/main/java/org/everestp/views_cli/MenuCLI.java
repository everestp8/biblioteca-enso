package org.everestp.views_cli;

import org.everestp.controllers.UsuarioController;
import org.everestp.daos.*;
import org.everestp.models.Exemplar;
import org.everestp.models.Usuario;
import org.everestp.models.Livro;
import org.everestp.services.EmprestimoService;
import org.everestp.services.ExemplarService;
import org.everestp.services.LivroService;
import org.everestp.services.UsuarioService;

import java.util.Scanner;
import org.everestp.services.RenovacaoService;

public class MenuCLI {

    private final Scanner scan = new Scanner(System.in);

    private final UsuarioView usuarioView;
    private final LivroView livroView;
    private final EmprestimoView emprestimoView;
    private Usuario usuario;
    

    public MenuCLI(Usuario usuario, UsuarioController usuarioController, LivroService livroService, ExemplarService exemplarService, EmprestimoService emprestimoService, RenovacaoService renovacaoService) {
        this.usuario = usuario;
        
        this.usuarioView = new UsuarioView(usuarioController);
        this.livroView = new LivroView(livroService, exemplarService);
        this.emprestimoView = new EmprestimoView(emprestimoService, exemplarService, livroService, renovacaoService);

        int opcao;
        boolean sair;
        do {
            if (this.estaAutenticado()) {
                this.mostrarMenu();
            } else {
                this.mostrarMenuDesautenticado();
            }

            opcao = this.lerOpcao();
            sair = this.estaAutenticado() ? this.handleOpcao(opcao) : this.handleOpcaoDesautenticado(opcao);
        } while (!sair);
    }

    private boolean handleOpcaoDesautenticado(int opcao) {
        boolean sair = false;
        switch (opcao) {
            case 0:
                sair = true;
                break;
            case 1:
                this.usuario = this.usuarioView.fazerLogin();
                break;
            case 2:
                this.usuario = this.usuarioView.cadastrarCliente();
                break;
            default:
                System.out.println("Opção inválida!");
        }
        return sair;
    }

    private boolean handleOpcao(int opcao) {
        boolean sair = false;
        boolean matched1 = true, matched2 = true;

        switch (opcao) {
            case 0:
                sair = true;
                break;
            case 1:
                this.usuarioView.alterarDadosDaConta(this.usuario.getId());
                break;
            case 2:
                this.livroView.listarCatalogoLivros();
                break;
            case 3:
                this.emprestimoView.devolverExemplarEmprestado(this.usuario.getId());
                break;
            case 4:
                this.emprestimoView.listarEmprestimosUsuario(this.usuario.getId());
                break;
            case 5:
                this.emprestimoView.renovarEmprestimo(this.usuario.getId());
                break;
            case 6:
                int aux = this.usuarioView.excluirConta(this.usuario.getId());
                if (aux == 0) {
                    this.usuario = null;
                    return sair;
                }
                break;
            default:
                matched1 = false;
                if (this.usuario.getPapel() != 2)
                    break;
                System.out.println("Opção inválida!");
                return sair;
        }

        switch (opcao) {
            case 7:
                this.livroView.adicionarExemplarLivro();
                break;
            case 8:
                this.livroView.removerExemplarLivro();
                break;
            case 9:
                this.livroView.inserirLivro();
                break;
            case 10:
                this.livroView.removerLivro();
                break;
            case 11:
                this.livroView.alterarDadosDoLivro();
                break;
            case 12:
                this.emprestimoView.novoEmprestimo();
                break;
            default:
                matched2 = false;
                if (matched1 || this.usuario.getPapel() != 1)
                    break;
                System.out.println("Opção inválida!!");
                return sair;
        }

        switch (opcao) {
            case 13:
                this.usuarioView.cadastrarUsuario();
                break;
            case 14:
                this.usuarioView.removerUsuario();
                break;
            default:
                if (matched1 || matched2 || this.usuario.getPapel() != 0)
                    break;
                System.out.println("Opção inválida!!!");
        }

        return sair;
    }

    private void mostrarMenuDesautenticado() {
        System.out.println("Escolha uma dessas opções abaixo: ");
        System.out.println();
        System.out.println("0 - Sair;");
        System.out.println("1 - Login;");
        System.out.println("2 - Cadastrar.");
    }

    private void mostrarMenu() {
        System.out.println("\nEscolha uma dessas opções abaixo: ");
        System.out.println();
        System.out.println("00 - Sair;");
        System.out.println("01 - Alterar dados da conta;");
        System.out.println("02 - Acessar catálogo de livros;");
        System.out.println("03 - Devolver livro;");
        System.out.println("04 - Histórico de empréstimos;");
        System.out.println("05 - Renovar empréstimo;");
        System.out.println("06 - Excluir conta;");

        if (this.usuario.getPapel() >= 2)
            return;

        System.out.println("07 - Adicionar exemplar;");
        System.out.println("08 - Remover exemplar;");
        System.out.println("09 - Inserir livro;");
        System.out.println("10 - Remover livro;");
        System.out.println("11 - Alterar dados de um livro;");
        System.out.println("12 - Emprestar livro;");

        if (this.usuario.getPapel() >= 1)
            return;

        System.out.println("13 - Cadastrar usuário;");
        System.out.println("14 - Remover usuário;");
    }

    private int lerOpcao() {
        System.out.print("> ");
        return scan.nextInt();
    }

    private boolean estaAutenticado() {
        return this.usuario != null;
    }
}
