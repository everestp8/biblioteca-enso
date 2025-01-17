package org.everestp.services;

import org.everestp.daos.LivroDAO;
import org.everestp.dtos.LivroDTO;
import org.everestp.models.Livro;

import java.util.List;

public class LivroService {

    private final LivroDAO livroDAO;

    public LivroService(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public Livro getLivroByTitulo(String titulo) {
        return this.livroDAO.getByTitulo(titulo);
    }

    public Livro getLivroById(int livroId) {
        return this.livroDAO.getById(livroId);
    }

    public List<Livro> getAllLivros() {
        return this.livroDAO.getAll();
    }

    public Livro cadastrarLivro(LivroDTO dados) {
        Livro livro = new Livro(0, dados.titulo(), dados.autor(), dados.genero(), dados.descricao(), dados.ano());
        this.livroDAO.save(livro);
        return livro;
    }

    public int excluirLivro(int livroID) {
        this.livroDAO.delete(livroID);
        return 0;
    }
    
    public int atualizarLivro(int livroId, LivroDTO dadosAtualizados) {
        Livro livroExistente = this.livroDAO.getById(livroId);
        Livro livroAtualizado;

        if (livroExistente == null) {
            return 1;
        }
        String novoTitulo = dadosAtualizados.titulo() != null ? dadosAtualizados.titulo() : livroExistente.getTitulo();
        String novoAutor = dadosAtualizados.autor() != null ? dadosAtualizados.autor() : livroExistente.getAutor();
        String novoGenero = dadosAtualizados.genero()!= null ? dadosAtualizados.genero(): livroExistente.getGenero();
        String novaDescricao = dadosAtualizados.descricao()!= null ? dadosAtualizados.descricao(): livroExistente.getDescricao();
        Integer novoAno = dadosAtualizados.ano()!= null ? dadosAtualizados.ano(): livroExistente.getAno();
        livroAtualizado = new Livro(livroExistente.getId(), novoTitulo, novoAutor, novoGenero, novaDescricao, novoAno);

        this.livroDAO.update(livroAtualizado);
        return 0;
    }
}
