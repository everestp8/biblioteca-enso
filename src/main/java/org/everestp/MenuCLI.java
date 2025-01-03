package org.everestp;

import org.everestp.models.Usuario;

import java.util.Scanner;

public class MenuCLI {

    private final Scanner scan = new Scanner(System.in);
    private final View view = new View();
    private Usuario usuario;

    public MenuCLI() {
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
                this.usuario = this.view.fazerLogin();
                break;
            case 2:
                this.usuario = this.view.cadastrarCliente();
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
                this.view.alterarDadosDaConta(this.usuario.getId());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                int aux = this.view.excluirConta(this.usuario.getId());
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
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            default:
                matched2 = false;
                if (matched1 || this.usuario.getPapel() != 1)
                    break;
                System.out.println("Opção inválida!!");
                return sair;
        }

        switch (opcao) {
            case 12:
                this.view.cadastrarUsuario();
                break;
            case 13:
                this.view.removerUsuario();
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
        System.out.println("2 - Cadastar.");
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

        System.out.println("08 - Inserir livro;");
        System.out.println("09 - Remover livro;");
        System.out.println("10 - Alterar dados de um livro;");
        System.out.println("11 - Emprestar livro;");

        if (this.usuario.getPapel() >= 1)
            return;

        System.out.println("12 - Cadastrar usuário;");
        System.out.println("13 - Remover usuário;");
    }

    private int lerOpcao() {
        System.out.print("> ");
        return scan.nextInt();
    }

    private boolean estaAutenticado() {
        return this.usuario != null;
    }
}
