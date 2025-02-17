package org.everestp.daos;

import org.everestp.exceptions.DatabaseException;
import org.everestp.models.Renovacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RenovacaoDAO extends DatabaseDAO<Renovacao> {
    @Override
    protected Renovacao mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Renovacao(
            rs.getInt("id"),
            rs.getInt("emprestimoFk"),
            rs.getInt("usuarioFk"),
            rs.getDate("dtRenovacao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    @Override
    protected String getTableName() {
        return "Renovacao";
    }

    public List<Renovacao> getByUsuarioIdAndEmprestimo(int usuarioId, int emprestimoId) {
        try {
            String query = "select * from Renovacao where usuarioFk = ? and emprestimoFk = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setInt(1, usuarioId);
            pstm.setInt(2, emprestimoId);

            ResultSet rs = pstm.executeQuery();
            List<Renovacao> renovacoes = new ArrayList<>();

            while (rs.next())
                renovacoes.add(mapResultSetToEntity(rs));

            return renovacoes;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os registros.", e);
        }
    }
}
