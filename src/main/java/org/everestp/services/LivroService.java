package org.everestp.services;

import org.everestp.daos.LivroDAO;
import org.everestp.dtos.LivroDTO;
import org.everestp.exceptions.DadosInvalidosException;
import org.everestp.exceptions.LivroNaoEncontradoException;
import org.everestp.models.Livro;
import org.everestp.utils.Validator;

import java.util.List;

public class LivroService {

    private final LivroDAO livroDAO;

    public LivroService(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public Livro getLivroByTitulo(String titulo) {
        Livro livro = this.livroDAO.getByTitulo(titulo);
        if (livro == null)
            throw new LivroNaoEncontradoException();
        return livro;
    }

    public Livro getLivroById(int livroId) {
        Livro livro = this.livroDAO.getById(livroId);
        if (livro == null)
            throw new LivroNaoEncontradoException();
        return livro;
    }

    public List<Livro> getAllLivros() {
        List<Livro> livros = this.livroDAO.getAll();
        if (livros == null)
            throw new LivroNaoEncontradoException();
        return livros;
    }

    public List<Livro> searchLivros(String tituloIncompleto) {
        List<Livro> livros = this.livroDAO.searchLivrosByTitulo(tituloIncompleto);
        if (livros == null)
            throw new LivroNaoEncontradoException();
        return livros;
    }

    public void cadastrarLivro(LivroDTO dados) {
        if (!Validator.validarLivroDTO(dados))
            throw new DadosInvalidosException("Dados inválidos para livro.");
        Livro livro = new Livro(0, dados.titulo(), dados.autor(), dados.genero(), dados.descricao(), dados.ano());
        this.livroDAO.save(livro);
    }

    public void excluirLivro(int livroId) {
        Livro livro = this.livroDAO.getById(livroId);
        if (livro == null)
            throw new LivroNaoEncontradoException();
        this.livroDAO.delete(livroId);
    }
    
    public void atualizarLivro(String titulo, LivroDTO dadosAtualizados) {
        if (!Validator.validarLivroDTO(dadosAtualizados))
            throw new DadosInvalidosException("Dados inválidos para livro.");
        Livro livroExistente = this.livroDAO.getByTitulo(titulo);
        Livro livroAtualizado;

        if (livroExistente == null)
            throw new LivroNaoEncontradoException();

        String novoTitulo = dadosAtualizados.titulo() != null ? dadosAtualizados.titulo() : livroExistente.getTitulo();
        String novoAutor = dadosAtualizados.autor() != null ? dadosAtualizados.autor() : livroExistente.getAutor();
        String novoGenero = dadosAtualizados.genero()!= null ? dadosAtualizados.genero(): livroExistente.getGenero();
        String novaDescricao = dadosAtualizados.descricao()!= null ? dadosAtualizados.descricao(): livroExistente.getDescricao();
        int novoAno = dadosAtualizados.ano()!= null ? dadosAtualizados.ano(): livroExistente.getAno();
        livroAtualizado = new Livro(livroExistente.getId(), novoTitulo, novoAutor, novoGenero, novaDescricao, novoAno);

        this.livroDAO.update(livroAtualizado);
    }

    public int countLivros() {
        return this.livroDAO.countAll();
    }
}
