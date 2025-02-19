package org.everestp.daos;

import org.everestp.exceptions.DatabaseException;
import org.everestp.models.Emprestimo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO extends DatabaseDAO<Emprestimo> {
    @Override
    protected Emprestimo mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Emprestimo(
                rs.getInt("id"),
                rs.getInt("exemplarFk"),
                rs.getInt("usuarioFk"),
                rs.getDate("dtEmprestimo").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                rs.getDate("dtDevolucao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                rs.getDate("dtPrazo").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    @Override
    protected String getTableName() {
        return "Emprestimo";
    }

    public List<Emprestimo> getByUsuarioFk(int usuarioId) {
        return this.getAllBy("usuarioFk", usuarioId);
    }

    public Emprestimo getAtivoByExemplarIdFisico(String idFIsico) {
        try {
            String query = "SELECT emp.* FROM Emprestimo emp JOIN Exemplar ex ON emp.exemplarFk = ex.id WHERE ex.idFisico = ? AND emp.dtDevolucao IS NULL;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setString(1, idFIsico);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next())
                throw new DatabaseException("Registro não encontrado.");

            return mapResultSetToEntity(rs);
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar por .", e);
        }
    }

    public void setDtPrazo(int emprestimoId, LocalDate novaDtPrazo) {
        try {
            String query = "update Emprestimmo set dtPrazo = ? where id = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setDate(1, (Date) Date.from(novaDtPrazo.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            pstm.setInt(2, emprestimoId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar o prazo.", e);
        }
    }

    public void setDtDevolucao(int emprestimoId, LocalDate novaDtDevolucao) {
        try {
            String query = "update Emprestimmo set dtDevolucao = ? where id = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setDate(1, (Date) Date.from(novaDtDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            pstm.setInt(2, emprestimoId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar a data de devolução.", e);
        }
    }
}
