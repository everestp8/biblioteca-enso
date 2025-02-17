package org.everestp.daos;

import org.everestp.models.Livro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDAO extends DatabaseDAO<Livro> {
    @Override
    protected Livro mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Livro(
            rs.getInt("id"),
            rs.getString("titulo"),
            rs.getString("autor"),
            rs.getString("genero"),
            rs.getString("descricao"),
            rs.getInt("ano")
        );
    }

    @Override
    protected String getTableName() {
        return "Livro";
    }

    public Livro getByTitulo(String titulo){
        return this.getBy("titulo", titulo);
    }
}
