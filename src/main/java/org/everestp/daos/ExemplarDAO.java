package org.everestp.daos;

import org.everestp.exceptions.DatabaseException;
import org.everestp.models.Exemplar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExemplarDAO extends DatabaseDAO<Exemplar> {
    @Override
    protected Exemplar mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Exemplar(
            rs.getInt("id"),
            rs.getInt("livroFk"),
            rs.getString("idFisico"),
            rs.getBoolean("disponivel")
        );
    }

    @Override
    protected String getTableName() {
        return "Exemplar";
    }

    public Exemplar getByIdFisico(String idFisico) {
        return this.getBy("idFisico", idFisico);
    }

    public List<Exemplar> getAllByLivroFk(int livroFk) {
        try {
            String query = "select * from Exemplar where livroFk = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setInt(1, livroFk);

            ResultSet rs = pstm.executeQuery();
            List<Exemplar> exemplares = new ArrayList<>();

            while (rs.next())
                exemplares.add(mapResultSetToEntity(rs));

            return exemplares;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void setDisponibilidadeById(int exemplarId, boolean disponibilidade) {
        try {
            String query = "update Exemplar set disponivel = ? where id = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setBoolean(1, disponibilidade);
            pstm.setInt(2, exemplarId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível mudar a disponibilidade do exemplar.", e);
        }
    }

    public void deleteByIdFisico(String idFisico) {
        this.deleteBy("idFisico", idFisico);
    }

    public void deleteByLivroFk(int livroFk) {
        this.deleteBy("livroFk", livroFk);
    }
}
