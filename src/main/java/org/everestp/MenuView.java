package org.everestp;

import org.everestp.models.Usuario;
import org.everestp.services.View;

import java.util.Scanner;

public class MenuView {
    private final Scanner scan = new Scanner(System.in);
    private final View view = new View();
    private Usuario usuario;

    public MenuView() {
        int opcao;
        boolean sair;
        do {
            opcao = this.estaAutenticado() ? this.lerOpcao() : this.lerOpcaoDesautenticado();
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
        switch (opcao) {
            case 0:
                sair = true;
                break;
            case 1:
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
                break;
            case 7:
                break;
            case 8:
                int aux = this.view.excluirConta(this.usuario.getId());
                if (aux == 0)
                    this.usuario = null;
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            default:
                System.out.println("Opção inválida!");
        }
        return sair;
    }

    private int lerOpcaoDesautenticado() {
        System.out.println("Escolha uma dessas opções abaixo: ");
        System.out.println();
        System.out.println("0 - Sair;");
        System.out.println("1 - Login;");
        System.out.println("2 - Cadastar.");
        return this.scan.nextInt();
    }
    private int lerOpcao() {
        System.out.println("Escolha uma dessas opções abaixo: ");
        System.out.println();
        System.out.println("00 - Sair;");
        System.out.println("01 - Alterar dados da conta;");
        System.out.println("02 - Alterar dados de um livro;");
        System.out.println("03 - Cadastrar bilbliotecário;");
        System.out.println("04 - Cadastrar usuário;");
        System.out.println("05 - Acessar catálogo de livros;");
        System.out.println("06 - Devolver livro;");
        System.out.println("07 - Emprestar livro;");
        System.out.println("08 - Excluir conta;");
        System.out.println("09 - Histórico de empréstimos;");
        System.out.println("10 - Inserir livro;");
        System.out.println("11 - Remover bibliotecário;");
        System.out.println("12 - Remover livro;");
        System.out.println("13 - Renovar empréstimo;");
        System.out.print("> ");
        return scan.nextInt();
    }

    private boolean estaAutenticado() {
        return this.usuario != null;
    }
}
