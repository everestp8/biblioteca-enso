package org.everestp.views_cli;

import org.everestp.controllers.UsuarioController;
import org.everestp.models.Usuario;

import java.util.Scanner;

public class UsuarioView {

    private final Scanner scan;
    private final Scanner scanLines;
    private final UsuarioController usuarioController;

    public UsuarioView(UsuarioController usuarioController) {
        this.scan = new Scanner(System.in);
        this.scanLines = new Scanner(System.in);
        this.usuarioController = usuarioController;
    }

    private int autenticarUsuario() {
        System.out.println("Digite seu e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();

        int code = this.usuarioController.autenticarUsuario(email, senha);
        if (code == 0) {
            System.out.println("Login efetuado com sucesso!");
        } else if (code == 1) {
            System.out.println("E-mail inválido!");
        } else if (code == 2) {
            System.out.println("Senha incorreta!");
        }

        return code;
    }

    public Usuario fazerLogin() {
        System.out.println("\n# Login");
        System.out.println("Digite seu e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();

        Usuario usuario = this.usuarioController.fazerLogin(email, senha);
        if (usuario == null) {
            System.out.println("Login efetuado com sucesso!");
        } else {
            System.out.println("Não foi possível efetuar o login, verifique suas credenciais.");
        }

        return usuario;
    }

    public void cadastrarUsuario() {
        System.out.println("\n# Cadastro de usuário");

        System.out.println("Digite o nome: ");
        String nome = scan.next();
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpf = scan.next();
        System.out.println("Digite o seu papel (0 - Admin, 1 - Bibliotecário, 2 - Cliente): ");
        int papel = scan.nextInt();

        Usuario usuario = this.usuarioController.cadastrarUsuario(nome, email, senha, cpf, papel);
        if (usuario != null) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o usuário.");
        }
    }

    public int excluirConta(int usuarioId) {
        System.out.println("\n# Excluir conta");
        int code = this.autenticarUsuario();
        if (code != 0) {
            return code;
        }

        code = this.usuarioController.excluirUsuario(usuarioId);
        if (code != 0) {
            System.out.println("Erro: Não foi possível excluir a conta.");
            return code;
        }

        System.out.println("Conta excluída com sucesso!");
        return 0;
    }

    public Usuario cadastrarCliente() {
        System.out.println("\n# Cadastro de cliente");
        System.out.println("Digite o nome: ");
        String nome = scan.next();
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpf = scan.next();

        Usuario usuario = this.usuarioController.cadastrarUsuario(nome, email, senha, cpf, 2);
        if (usuario != null) {
            System.out.println("Você foi cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível completar o cadastro.");
        }

        return usuario;
    }

    public void alterarDadosDaConta(int usuarioId) {
        System.out.println("\n# Alteração de dados da conta");
        int code = this.autenticarUsuario();

        if (code != 0) {
            return;
        }

        System.out.println("Digite . nas propriedades que deseja conservar.");

        System.out.println("Digite seu novo nome (ou .): ");
        String nome = scan.next();
        System.out.println("Digite seu novo e-mail (ou .): ");
        String email = scan.next();
        System.out.println("Digite sua nova senha (ou .): ");
        String senha = scanLines.nextLine();
        System.out.println("Digite um novo CPF (ou .): ");
        String cpf = scan.next();

        code = this.usuarioController.alterarDadosUsuario(usuarioId, nome, email, senha, cpf);

        if (code != 0) {
            System.out.println("Não foi possível alterar os dados da conta ;(");
            return;
        }

        System.out.println("Dados da conta atualizados com sucesso!");
    }

    public void removerUsuario() {
        System.out.println("\n# Remoção de usuário");
        System.out.println("Digite o ID da conta a ser removida: ");
        int id = scan.nextInt();

        int code = this.usuarioController.excluirUsuario(id);
        if (code != 0) {
            System.out.println("Erro: Não foi possível excluir a conta.");
            return;
        }
        System.out.println("Usuário removido com sucesso!");
    }
}
