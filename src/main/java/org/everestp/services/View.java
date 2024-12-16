package org.everestp.services;

import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.UsuarioDTO;
import org.everestp.models.Usuario;

import java.util.Scanner;

public class View {
    private Scanner scan = new Scanner(System.in);
    private Scanner scanLines = new Scanner(System.in);
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private UsuarioService usuarioService = new UsuarioService(this.usuarioDAO);

    public Usuario fazerLogin() {
        System.out.println("# Login");
        System.out.println("Digite seu e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        int code = this.usuarioService.autenticarUsuario(email, senha);

        if (code == 0) {
            System.out.println("Login efetuado com sucesso!");
        } else if (code == 1) {
            System.out.println("E-mail inválido!");
        } else if (code == 2) {
            System.out.println("Senha incorreta!");
        }

        return this.usuarioService.getUsuario(email);
    }

    public void cadastrarUsuario() {
        System.out.println("# Cadatro de usuário");
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpf = scan.next();
        System.out.println("Digite o seu papel (0 - Admin, 1 - Bibliotecário, 2 - Cliente): ");
        int papel = scan.nextInt();
        UsuarioDTO dadosUsuario = new UsuarioDTO(email, senha, cpf, papel);

        this.usuarioService.cadastrarUsuario(dadosUsuario);
    }

    public int excluirConta(int usuarioId) {
        System.out.println("# Excluir conta");
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();

        int code = this.usuarioService.autenticarUsuario(email, senha);
        if (code != 0) {
            System.out.println("Não foi possível excluir a conta.");
            return 1;
        }

        code = this.usuarioService.excluirUsuario(usuarioId);
        if (code != 0) {
            System.out.println("Não foi possível excluir a conta.");
            return 1;
        }

        System.out.println("Conta excluída com sucesso!");
        return 0;
    }

    public Usuario cadastrarCliente() {
        System.out.println("# Cadatro de usuário");
        System.out.println("Digite o email: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpf = scan.next();
        UsuarioDTO dadosUsuario = new UsuarioDTO(email, senha, cpf, 2);

        return this.usuarioService.cadastrarUsuario(dadosUsuario);
    }
}
