package org.everestp.controllers;

import org.everestp.dtos.LivroDTO;
import org.everestp.models.Exemplar;
import org.everestp.models.Livro;
import org.everestp.services.ExemplarService;
import org.everestp.services.LivroService;

import java.util.List;

public class LivroController {
    private final LivroService livroService;
    private final ExemplarService exemplarService;

    public LivroController(LivroService livroService, ExemplarService exemplarService) {
        this.livroService = livroService;
        this.exemplarService = exemplarService;
    }

    public Response<List<Livro>> listarLivros() {
        try {
            List<Livro> livros = this.livroService.getAllLivros();
            return Response.sucesso(livros);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Livro> livroPorId(int livroId) {
        try {
            Livro livro = this.livroService.getLivroById(livroId);
            return Response.sucesso(livro);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Livro> livroPorTitulo(String titulo) {
        try {
            Livro livro = this.livroService.getLivroByTitulo(titulo);
            return Response.sucesso(livro);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<List<Exemplar>> listarExemplares(String tituloLivro) {
        try {
            List<Exemplar> exemplares = this.exemplarService.getExemplaresByTitulo(tituloLivro);
            return Response.sucesso(exemplares);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }
    
    public Response<Exemplar> exemplarPorId(int exemplarId) {
        try {
            Exemplar exemplar = this.exemplarService.getExemplarById(exemplarId);
            return Response.sucesso(exemplar);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<List<Exemplar>> inserirExemplares(String titulo, int quantExemplares) {
        try {
            List<Exemplar> exemplares = this.exemplarService.adicionarExemplarPorTitulo(titulo, quantExemplares);
            return Response.sucesso(exemplares);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }
    
    public Response<Void> inserirLivro(LivroDTO dadosLivro, int quantExemplares) {
        try {
            this.exemplarService.adicionarExemplarPorTitulo(dadosLivro.titulo(), quantExemplares);
            this.livroService.cadastrarLivro(dadosLivro);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> excluirLivro(String titulo) {
        try {
            Livro livro = this.livroService.getLivroByTitulo(titulo);
            this.livroService.excluirLivro(livro.getId());
            this.exemplarService.removerExemplaresByLivroId(livro.getId());
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> alterarDadosDoLivro(String titulo, LivroDTO dadosLivro) {
        try {
            this.livroService.atualizarLivro(titulo, dadosLivro);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> removerExemplarDoLivro(String idFisico) {
        try {
            this.exemplarService.removerExemplarByIdFisico(idFisico);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }
}
