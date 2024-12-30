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
    private Usuario usuario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private UsuarioService usuarioService = new UsuarioService(this.usuarioDAO);

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
        System.out.println("\n# Cadatro de usuário");

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
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();

        int code = this.usuarioService.autenticarUsuario(email, senha);
        if (code != 0) {
            System.out.println("Erro: Não foi possível excluir a conta.");
            return 1;
        }

        code = this.usuarioService.excluirUsuario(usuarioId);
        if (code != 0) {
            System.out.println("Erro: Não foi possível excluir a conta.");
            return 1;
        }

        System.out.println("Conta excluída com sucesso!");
        return 0;
    }

    public Usuario cadastrarCliente() {
        System.out.println("\n# Cadatro de usuário");
        System.out.println("Digite o e-mail: ");
        String email = scan.next();
        System.out.println("Digite sua senha: ");
        String senha = scanLines.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpf = scan.next();
        UsuarioDTO dadosUsuario = new UsuarioDTO(email, senha, cpf, 2);

        return this.usuarioService.cadastrarUsuario(dadosUsuario);
    }

    public void alterarDadosDaConta() {
        System.out.println("\n# Alteração de dados da conta");
        System.out.print("Digite o email da conta a ser alterada para verificar sua autenticação: ");
        String email = scan.next();

        this.usuario = this.usuarioService.getUserByEmail(email);

        if (this.usuario == null) {
            System.out.println("Erro: Usuário não encontrado. Por favor, verifique o email fornecido.");
            return;
        }

        System.out.println("O que você deseja alterar? (0 - Email, 1 - Senha: ");
        int alterarDados = scan.nextInt();

        switch (alterarDados) {
            case 0:
                System.out.println("\n# Alterar Email.");
                System.out.print("Digite um novo email: ");
                String newEmail = scan.next();

                if (this.usuarioService.getUserByEmail(newEmail) != null) {
                    System.out.println("Email já cadastrado. Por favor, tente outro.");
                } else {
                    this.usuario.setEmail(newEmail);
                    System.out.println("Email alterado com sucesso.");
                }
                break;

            case 1:
                System.out.println("\n# Alterar Senha.");
                System.out.print("Digite uma nova senha: ");
                String newSenha = scanLines.nextLine();

                if (newSenha.equals(this.usuario.getSenha())) {
                    System.out.println("A nova senha não pode ser igual à senha atual.");
                } else {
                    this.usuario.setSenha(newSenha);
                    System.out.println("Senha alterada com sucesso.");
                }
                break;

            default:
                System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                return;
        }

        UsuarioDTO dadosAtualizados = new UsuarioDTO(this.usuario.getEmail(), this.usuario.getSenha(), this.usuario.getCpf(), this.usuario.getPapel());

        this.usuarioService.atualizarUsuario(dadosAtualizados);
        System.out.println("Dados da conta atualizados com sucesso!");
    }

    public void removerUsuario() {
        System.out.println("\n# Remoção de usuário");
        System.out.print("Digite o email da conta a ser removida para verificar sua autenticação: ");
        String email = scan.next();

        this.usuario = this.usuarioService.getUserByEmail(email);

        if (this.usuario == null) {
            System.out.println("Erro: Usuário não encontrado. Por favor, verifique o email fornecido.");
            return;
        }
        
        System.out.println("Usuário encontrado.");
        System.out.print("Digite o CPF do usuário a ser removido: ");
        String cpf = scan.next();

        this.usuario = this.usuarioDAO.getByCpf(cpf);

        if (this.usuario == null) {
            System.out.println("Erro: Usuário não encontrado. Por favor, verifique o CPF fornecido.");
            return;
        }
        if (this.usuario != null) {
            this.usuario.setAtivo(false);
            this.usuarioService.excluirUsuario(0);
        }
        System.out.println("Usuário removido com sucesso!");
    }
}
