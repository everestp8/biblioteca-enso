package org.everestp.services;

import org.everestp.daos.ExemplarDAO;
import org.everestp.daos.LivroDAO;
import org.everestp.models.Exemplar;
import org.everestp.models.Livro;

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
        return this.exemplarDAO.getById(exemplarId);
    }

    public List<Exemplar> getExemplaresByTitulo(String tituloLivro) {
        Livro livro = this.livroDAO.getByTitulo(tituloLivro);

        if (livro == null)
            return null;

        return this.exemplarDAO.getAllByLivroFk(livro.getId());
    }

    public int adicionarExemplarPorTitulo(String tituloLivro, int quantidade) {
        Livro livro = this.livroDAO.getByTitulo(tituloLivro);

        if (livro == null)
            return 1;

        for (int i=0; i<quantidade; i++) {
            Exemplar novoExemplar = new Exemplar(0, livro.getId(), this.geradorIdFisico(), true);
            this.exemplarDAO.save(novoExemplar);
        }
        return 0;
    }

    public int removerExemplarByIdFisico(String idFisico) {
        try {
            this.exemplarDAO.deleteByIdFisico(idFisico);
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }

    public int remocveExemplaresByLivroId(int livroId) {
        for (Exemplar e : this.exemplarDAO.getAllByLivroFk(livroId))
            this.exemplarDAO.delete(e.getId());
        return 0;
    }
}
