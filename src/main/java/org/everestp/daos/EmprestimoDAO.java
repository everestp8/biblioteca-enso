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
        java.sql.Date sqlDateEmprestimo = rs.getDate("dtEmprestimo");
        LocalDate dtEmprestimo = (sqlDateEmprestimo != null) ? sqlDateEmprestimo.toLocalDate() : null;

        java.sql.Date sqlDateDevolucao = rs.getDate("dtDevolucao");
        LocalDate dtDevolucao = (sqlDateDevolucao != null) ? sqlDateDevolucao.toLocalDate() : null;

        java.sql.Date sqlDatePrazo = rs.getDate("dtPrazo");
        LocalDate dtPrazo = (sqlDatePrazo != null) ? sqlDatePrazo.toLocalDate() : null;

        return new Emprestimo(
                rs.getInt("id"),
                rs.getInt("exemplarFk"),
                rs.getInt("usuarioFk"),
                dtEmprestimo,
                dtDevolucao,
                dtPrazo
        );
    }

    @Override
    protected String getTableName() {
        return "Emprestimo";
    }

    public List<Emprestimo> getByUsuarioFk(int usuarioId) {
        return this.getAllBy("usuarioFk", usuarioId);
    }

    public List<Emprestimo> getBetweenDates(LocalDate beginDate, LocalDate endDate) {
        try {
            String query = "select * from " + getTableName() + " where dtEmprestimo between ? and ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setDate(1, java.sql.Date.valueOf(beginDate));
            pstm.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet rs = pstm.executeQuery();
            List<Emprestimo> entities = new ArrayList<>();

            while (rs.next())
                entities.add(mapResultSetToEntity(rs));

            return entities;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os registros.", e);
        }
    }

    public Emprestimo getAtivoByExemplarIdFisico(String idFIsico) {
        try {
            String query = "SELECT emp.* FROM Emprestimo emp JOIN Exemplar ex ON emp.exemplarFk = ex.id WHERE ex.idFisico = ? AND emp.dtDevolucao IS NULL;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setString(1, idFIsico);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next())
                throw new DatabaseException("Empréstimo não encontrado.");

            return mapResultSetToEntity(rs);
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar por empréstimo.", e);
        }
    }

    public void setDtPrazo(int emprestimoId, LocalDate novaDtPrazo) {
        try {
            String query = "update Emprestimo set dtPrazo = ? where id = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setDate(1, java.sql.Date.valueOf(novaDtPrazo));
            pstm.setInt(2, emprestimoId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar o prazo.", e);
        }
    }

    public void setDtDevolucao(int emprestimoId, LocalDate novaDtDevolucao) {
        try {
            String query = "update Emprestimo set dtDevolucao = ? where id = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setDate(1, java.sql.Date.valueOf(novaDtDevolucao));
            pstm.setInt(2, emprestimoId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar a data de devolução.", e);
        }
    }
}
