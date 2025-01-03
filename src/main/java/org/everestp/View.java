package org.everestp;

import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.UsuarioDTO;
import org.everestp.models.Usuario;

import java.util.Scanner;
import org.everestp.services.UsuarioService;
import org.everestp.services.UsuarioService;

public class View {

    private Scanner scan = new Scanner(System.in);
    private Scanner scanLines = new Scanner(System.in);
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private UsuarioService usuarioService = new UsuarioService(this.usuarioDAO);

    private int autenticarUsuario() {
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();

        int code = this.usuarioService.autenticarUsuario(email, senha);
        if (code != 0) {
            System.out.println("Erro: Não foi possível autenticar.");
            return 1;
        }

        return 0;
    }

    public Usuario fazerLogin() {
        System.out.println("\n# Login");
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

        return this.usuarioService.getUserByEmail(email);
    }

    public void cadastrarUsuario() {
        System.out.println("\n# Cadastro de usuário");

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
        System.out.println("Usuário cadastrado!");

    }

    public int excluirConta(int usuarioId) {
        System.out.println("\n# Excluir conta");
        int code = this.autenticarUsuario();

        if (code != 0)
            return code;

        code = this.usuarioService.excluirUsuario(usuarioId);
        if (code != 0) {
            System.out.println("Erro: Não foi possível excluir a conta.");
            return 1;
        }

        System.out.println("Conta excluída com sucesso!");
        return 0;
    }

    public Usuario cadastrarCliente() {
        System.out.println("\n# Cadastro de cliente");
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpf = scan.next();
        UsuarioDTO dadosUsuario = new UsuarioDTO(email, senha, cpf, 2);

        return this.usuarioService.cadastrarUsuario(dadosUsuario);
    }

    public void alterarDadosDaConta(int usuarioId) {
        System.out.println("\n# Alteração de dados da conta");
        int code = this.autenticarUsuario();

        if (code != 0)
            return;

        System.out.println("Apenas dê <Enter> nas propriedades que deseja conservar.");
        System.out.println("Digite seu novo e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua nova senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite um novo CPF: ");
        String cpf = scan.next();

        UsuarioDTO dadosAtualizados = new UsuarioDTO(email, senha, cpf, 2);

        code = this.usuarioService.atualizarUsuario(usuarioId, dadosAtualizados);

        if (code != 0) {
            System.out.println("Não foi possível alterar os dados da conta ;(");
            return;
        }

        System.out.println("Dados da conta atualizados com sucesso!");
    }

    public void removerUsuario() {
        System.out.println("\n# Remoção de usuário");

        System.out.println("Digite o email da conta a ser removida: ");
        String email = scan.next();

        Usuario usuario = this.usuarioService.getUserByEmail(email);

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado. Por favor, verifique o email fornecido.");
            return;
        }

        int code = this.usuarioService.excluirUsuario(usuario.getId());
        if (code != 0) {
            System.out.println("Erro: Não foi possível excluir a conta.");
            return;
        }
        System.out.println("Usuário removido com sucesso!");
    }
}
