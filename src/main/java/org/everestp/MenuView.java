package org.everestp;

import org.everestp.services.View;

import java.util.Scanner;

public class MenuView {
    private Scanner scan = new Scanner(System.in);
    private View view = new View();
    private boolean autenticado;
    public MenuView() {
        int opcao;
        do {
            opcao = autenticado ? lerOpcao() : lerOpcaoDesautenticado();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    this.autenticado = this.view.fazerLogin() == 0;
                    break;
                case 2:
                    this.view.cadastrarCliente();
                    this.autenticado = true;
                    break;
            }
        } while (opcao != 0);
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
        System.out.println("0 - Sair;");
        System.out.println("1 - Alterar dados da conta;");
        System.out.println("2 - Alterar dados de um livro;");
        System.out.println("3 - Cadastrar bilbliotecário;");
        System.out.println("4 - Cadastrar usuário;");
        System.out.println("5 - Acessar catálogo de livros;");
        System.out.println("6 - Devolver livro;");
        System.out.println("7 - Emprestar livro;");
        System.out.println("8 - Excluir conta;");
        System.out.println("9 - Histórico de empréstimos;");
        System.out.println("10 - Inserir livro;");
        System.out.println("11 - Remover bibliotecário;");
        System.out.println("12 - Remover livro;");
        System.out.println("13 - Renovar empréstimo;");
        System.out.print("> ");
        return scan.nextInt();
    }
}
