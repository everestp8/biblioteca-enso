package org.everestp.daos;

import org.everestp.models.Livro;

public class LivroDAO extends InMemoryDAO<Livro> {
    public Livro getByTitulo(String titulo){
        for (Livro l : this.getAll())
            if (l.getTitulo().equalsIgnoreCase(titulo))
                return l;
        return null;
    }
}
