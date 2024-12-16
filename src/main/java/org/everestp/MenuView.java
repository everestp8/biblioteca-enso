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
        System.out.println("Escolha uma dessas opções abaixo:\n");
        System.out.println("0 - Sair;");
        System.out.println("1 - Login;");
        System.out.println("2 - Cadastar.");
        return this.scan.nextInt();
    }
    private int lerOpcao() {
        System.out.println("Escolha uma dessas opções abaixo:\n");
        System.out.println("""
                           +----+----------------------------+
                           | 0  | Sair                       |
                           +----+----------------------------+
                           | 1  | Alterar dados da conta     |
                           +----+----------------------------+
                           | 2  | Alterar dados de um livro  |
                           +----+----------------------------+
                           | 3  | Cadastrar bibliotecário    |
                           +----+----------------------------+
                           | 4  | Cadastrar usuário          |
                           +----+----------------------------+
                           | 5  | Acessar catálogo de livros |
                           +----+----------------------------+
                           | 6  | Devolver livro             |
                           +----+----------------------------+
                           | 7  | Emprestar livro            |
                           +----+----------------------------+
                           | 8  | Excluir conta              |
                           +----+----------------------------+
                           | 9 | Histórico de empréstimos    |
                           +----+----------------------------+
                           | 10 | Inserir livro              |
                           +----+----------------------------+
                           | 11 | Remover bibliotecário      |
                           +----+----------------------------+
                           | 12 | Remover livro              |
                           +----+----------------------------+
                           | 13 | Renovar empréstimo         |
                           +----+----------------------------+""");
        System.out.print("> ");
        return scan.nextInt();
    }
}
