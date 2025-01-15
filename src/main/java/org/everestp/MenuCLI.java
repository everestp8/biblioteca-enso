package org.everestp;

import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.daos.UsuarioDAO;
import org.everestp.models.Exemplar;
import org.everestp.models.Usuario;
import org.everestp.models.Livro;
import org.everestp.views.LivroView;
import org.everestp.views.UsuarioView;

import java.util.Scanner;

public class MenuCLI {

    private final Scanner scan = new Scanner(System.in);

    private final UsuarioDAO usuarioDAO;
    private final LivroDAO livroDAO;

    private final UsuarioView usuarioView;
    private final LivroView livroView;
    private final ExemplarDAO exemplarDAO;
    private Usuario usuario;

    public MenuCLI() {
        this.usuarioDAO = new UsuarioDAO();
        this.livroDAO = new LivroDAO();
        this.exemplarDAO = new ExemplarDAO();

        // Dados de Teste
        this.usuarioDAO.save(new Usuario(0, "@@@", "123", "cpf1", 1));
        this.usuario = this.usuarioDAO.getById(1);

        this.livroDAO.save(new Livro(0, "l1", "autor1", "genero1", "desc1", 2001));
        this.livroDAO.save(new Livro(0, "l2", "autor2", "genero2", "desc2", 2002));
        this.livroDAO.save(new Livro(0, "l3", "autor3", "genero3", "desc3", 2003));

        this.exemplarDAO.save(new Exemplar(0, 1, "aaabbb", true));
        this.exemplarDAO.save(new Exemplar(0, 1, "baabbb", false));
        this.exemplarDAO.save(new Exemplar(0, 2, "aaabbc", false));
        this.exemplarDAO.save(new Exemplar(0, 2, "baabbc", false));
        this.exemplarDAO.save(new Exemplar(0, 2, "caabbc", true));
        this.exemplarDAO.save(new Exemplar(0, 3, "aaabbd", true));

        this.usuarioView = new UsuarioView(this.usuarioDAO);
        this.livroView = new LivroView(this.livroDAO, this.exemplarDAO);

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
                break;
            case 4:
                break;
            case 5:
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
