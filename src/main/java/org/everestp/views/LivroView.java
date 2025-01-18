package org.everestp.views;

import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.dtos.LivroDTO;
import org.everestp.models.Exemplar;
import org.everestp.models.Livro;

import java.util.Scanner;
import org.everestp.daos.UsuarioDAO;
import org.everestp.services.ExemplarService;
import org.everestp.services.UsuarioService;
import org.everestp.services.LivroService;

public class LivroView {

    private final Scanner scan;
    private final Scanner scanLines;
    private final LivroService livroService;
    private final ExemplarService exemplarService;

    public LivroView(LivroService livroService, ExemplarService exemplarService) {
        this.scan = new Scanner(System.in);
        this.scanLines = new Scanner(System.in);
        this.livroService = livroService;
        this.exemplarService = exemplarService;
    }
    
    public void listarCatalogoLivros() {
        System.out.println("# Catálogo de livros: ");

        for (Livro l : this.livroService.getAllLivros()) {
            System.out.println("Id: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.printf("Autor: %s, %d\n", l.getAutor(), l.getAno());
            System.out.println("Gênero: " + l.getGenero());
            System.out.println("Descrição: " + l.getDescricao());
            System.out.println("Exemplares: ");
            for (Exemplar e : this.exemplarService.getExemplaresByTitulo(l.getTitulo())) {
                System.out.printf("%s : %s\n", e.getIdFisico(), e.getDisponivel() ? "Disponível" : "Não disponível.");
            }
            System.out.println();
        }
    }

    public void inserirLivro() {
        System.out.println("# Inserindo livro.");

        System.out.println("Digite o título do livro: ");
        String titulo = scanLines.nextLine();
        System.out.println("Digite o nome do autor: ");
        String autor = scanLines.nextLine();
        System.out.println("Digite o gênero do livro: ");
        String genero = scanLines.nextLine();
        System.out.println("Digite a descrição do livro: ");
        String descricao = scanLines.nextLine();
        System.out.println("Digite a quantidade de exemplares: ");
        int quantExemplares = scan.nextInt();
        System.out.println("Por fim, digite o ano em que o livro foi publicado: ");
        int ano = scan.nextInt();

        LivroDTO dadosLivro = new LivroDTO(titulo, autor, genero, descricao, ano);
        this.livroService.cadastrarLivro(dadosLivro);

        // TODO: Provavelmente essa função deveria estar dentro de LivoService.cadastrarLivro()
        this.exemplarService.adicionarExemplarPorTitulo(titulo, quantExemplares);

        System.out.println("Livro cadastrado com sucesso!");
    }

    public void removerLivro() {
        System.out.println("\n# Remoção de livro");
        System.out.println("Digite o título do livro a ser removido: ");
        String titulo = scanLines.nextLine();

        Livro livro = this.livroService.getLivroByTitulo(titulo);
        if (livro == null) {
            System.out.println("Erro: Livro não encontrado. Por favor, verifique o título fornecido.");
            return;
        }

        int code = this.exemplarService.remocveExemplaresByLivroId(livro.getId());
        if (code != 0) {
            System.out.println("Erro: Não foi possível remover o livro.");
            return;
        }

        code = this.livroService.excluirLivro(livro.getId());
        if (code != 0) {
            System.out.println("Erro: Não foi possível remover o livro.");
            return;
        }

        System.out.println("Livro removido com sucesso!");
    }

    public void alterarDadosDoLivro() {
        System.out.println("# Alteração de dados de um livro.");
        System.out.println("Digite o título do livro a ser alterado: ");
        String tituloParaBusca = scanLines.nextLine();

        Livro livro = this.livroService.getLivroByTitulo(tituloParaBusca);
        if (livro == null) {
            System.out.println("Erro: Livro não encontrado. Por favor, verifique o título fornecido.");
            return;
        }

        System.out.println("Digite . nas propriedades que deseja conservar.");
        String aux;

        System.out.println("Digite um novo título do livro (ou .): ");
        aux = scanLines.nextLine();
        String titulo = aux.charAt(0) == '.' ? null : aux;
        System.out.println("Digite um novo autor (ou .): ");
        aux = scanLines.nextLine();
        String autor = aux.charAt(0) == '.' ? null : aux;
        System.out.println("Digite um novo gênero (ou .): ");
        aux = scanLines.nextLine();
        String genero = aux.charAt(0) == '.' ? null : aux;
        System.out.println("Digite uma nova descrição para o livro (ou .): ");
        aux = scanLines.nextLine();
        String descricao = aux.charAt(0) == '.' ? null : aux;
        System.out.println("Digite um novo ano de publicação (ou .): ");
        aux = scanLines.nextLine();
        Integer ano = null;
        if (aux.charAt(0) != '.') {
            try {
                ano = Integer.parseInt(aux);
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido! Digite um número inteiro.");
                return;
            }
        }

        LivroDTO dadosAtualizados = new LivroDTO(titulo, autor, genero, descricao, ano);
        int code = this.livroService.atualizarLivro(livro.getId(), dadosAtualizados);

        if (code != 0) {
            System.out.println("Não foi possível alterar os dados do livro ;(");
            return;
        }
        System.out.println("Dados do livro atualizados com sucesso!");
    }

    public void adicionarExemplarLivro() {
        System.out.println("# Adicionar exemplar a um livro: ");
        System.out.println("Digite o título do livro que deseja adicionar exemplares: ");
        String titulo = scanLines.nextLine();
        System.out.println("Digite a quantidade de exemplares que deseja adicionar: ");
        int quantNovosExemplares = scan.nextInt();
        int code = this.exemplarService.adicionarExemplarPorTitulo(titulo, quantNovosExemplares);
        if (code != 0) {
            System.out.println("Não foi possível adicionar os exemplares.");
            return;
        }
        System.out.println("Exemplares adicionados com sucesso!");
    }

    public void removerExemplarLivro() {
        System.out.println("# Remover exemplar de um livro: ");
        System.out.println("Digite o Id físico do exemplar: ");
        String idFisico = scan.next();
        int code = this.exemplarService.removerExemplarByIdFisico(idFisico);
        if (code != 0) {
            System.out.println("Não foi possível remover o exemplar.");
        }
        System.out.println("O exemplar foi removido com sucesso!");
    }
}
