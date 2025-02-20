package org.everestp.daos;

import org.everestp.exceptions.DatabaseException;
import org.everestp.models.Livro;
import org.everestp.models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Livro> searchLivrosByTitulo(Object value) {
        try {
            String query = "select * from " + getTableName() + " where titulo like ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setObject(1, "%" + value + "%");

            ResultSet rs = pstm.executeQuery();
            List<Livro> entities = new ArrayList<>();

            while (rs.next())
                entities.add(mapResultSetToEntity(rs));

            return entities;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao pesquisar livros.", e);
        }
    }
}
