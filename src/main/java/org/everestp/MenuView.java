package org.everestp;

import java.util.Scanner;

public class MenuView {

    public MenuView() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha uma dessas opções abaixo:\n");
        System.out.println("""
                           +----+----------------------------+
                           | 1  | Login                      |
                           +----+----------------------------+
                           | 2  | Alterar dados da conta     |
                           +----+----------------------------+
                           | 3  | Alterar dados de um livro  |
                           +----+----------------------------+
                           | 4  | Cadastrar bibliotecário    |
                           +----+----------------------------+
                           | 5  | Cadastrar cliente          |
                           +----+----------------------------+
                           | 6  | Acessar catálogo de livros |
                           +----+----------------------------+
                           | 7  | Devolver livro             |
                           +----+----------------------------+
                           | 8  | Emprestar livro            |
                           +----+----------------------------+
                           | 9  | Excluir conta              |
                           +----+----------------------------+
                           | 10 | Histórico de empréstimos   |
                           +----+----------------------------+
                           | 11 | Inserir livro              |
                           +----+----------------------------+
                           | 12 | Remover bibliotecário      |
                           +----+----------------------------+
                           | 13 | Remover livro              |
                           +----+----------------------------+
                           | 14 | Renovar empréstimo         |
                           +----+----------------------------+""");
        System.out.print("> ");
        int opcao = scan.nextInt();
    }
}
