package org.everestp.models;

public class Livro implements Model {
    private final int id;
    private final String titulo, autor, genero, descricao;
    private final int ano;

    public Livro(int id, String titulo, String autor, String genero, String descricao, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descricao = descricao;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getAno() {
        return ano;
    }
}
