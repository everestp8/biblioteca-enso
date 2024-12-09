package org.everestp.models;

public class Livro {
    private int id;
    private String titulo, autor, genero, descricao, ano;

    public Livro(int id, String titulo, String autor, String genero, String descricao, String ano) {
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

    public String getAno() {
        return ano;
    }
}
