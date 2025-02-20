package org.everestp.services;

import java.util.ArrayList;
import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.exceptions.DadosInvalidosException;
import org.everestp.exceptions.ExemplarNaoEncontradoException;
import org.everestp.exceptions.ExemplaresEmUsoException;
import org.everestp.exceptions.LivroNaoEncontradoException;
import org.everestp.models.Exemplar;
import org.everestp.models.Livro;
import org.everestp.utils.Validator;

import java.util.List;
import java.util.Random;

public class ExemplarService {
    private final ExemplarDAO exemplarDAO;
    private final LivroDAO livroDAO;

    public ExemplarService(ExemplarDAO exemplarDAO, LivroDAO livroDAO) {
        this.exemplarDAO = exemplarDAO;
        this.livroDAO = livroDAO;
    }

    private String geradorIdFisico() {
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int tamanho = 6;

        StringBuilder idFisico = new StringBuilder();
        Random rnd = new Random();
        while (idFisico.length() < tamanho) {
            int index = (int) (rnd.nextFloat() * caracteres.length());
            idFisico.append(caracteres.charAt(index));
        }

        return idFisico.toString();
    }

    public Exemplar getExemplarById(int exemplarId) {
        Exemplar exemplar = this.exemplarDAO.getById(exemplarId);
        if (exemplar == null)
            throw new ExemplarNaoEncontradoException();
        return exemplar;
    }

    public List<Exemplar> getExemplaresByTitulo(String tituloLivro) {
        Livro livro = this.livroDAO.getByTitulo(tituloLivro);
        if (livro == null)
            throw new LivroNaoEncontradoException();

        List<Exemplar> exemplares = this.exemplarDAO.getAllByLivroFk(livro.getId());
        if (exemplares == null)
            throw new ExemplarNaoEncontradoException();
        return exemplares;
    }

    public List<Exemplar> adicionarExemplarPorTitulo(String tituloLivro, int quantidade) {
        Livro livro = this.livroDAO.getByTitulo(tituloLivro);

        if (livro == null)
            throw new LivroNaoEncontradoException();
        
        List<Exemplar> exemplares = new ArrayList<>();
        for (int i=0; i<quantidade; i++) {
            Exemplar novoExemplar = new Exemplar(0, livro.getId(), this.geradorIdFisico(), true);
            this.exemplarDAO.save(novoExemplar);
            exemplares.add(novoExemplar);
        }
        return exemplares;
    }

    public void removerExemplarByIdFisico(String idFisico) {
        if (!Validator.validarIdFisico(idFisico))
            throw new DadosInvalidosException("ID físico inválido.");
        Exemplar exemplar = this.exemplarDAO.getByIdFisico(idFisico);
        if (exemplar == null)
            throw new ExemplarNaoEncontradoException();
        if (!exemplar.getDisponivel())
            throw new ExemplaresEmUsoException();
        this.exemplarDAO.deleteByIdFisico(idFisico);
    }

    public void removerExemplaresByLivroId(int livroId) {
        for (Exemplar e : this.exemplarDAO.getAllByLivroFk(livroId))
            if (!e.getDisponivel())
                throw new ExemplaresEmUsoException();
        this.exemplarDAO.deleteByLivroFk(livroId);
    }

    public int countExemplares() {
        return this.exemplarDAO.countAll();
    }
}
