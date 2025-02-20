package org.everestp.daos;

import org.everestp.models.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends DatabaseDAO<Usuario> {
    @Override
    protected Usuario mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("senha"),
            rs.getString("cpf"),
            rs.getInt("papel")
        );
    }

    @Override
    protected String getTableName() {
        return "Usuario";
    }

    public Usuario getByEmail(String email) {
        return this.getBy("email", email);
    }

    public Usuario getByCpf(String cpf) {
        return this.getBy("cpf", cpf);
    }
}
