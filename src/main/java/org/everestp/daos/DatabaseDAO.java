package org.everestp.daos;

import org.everestp.DatabaseConnection;
import org.everestp.exceptions.DatabaseException;
import org.everestp.models.Model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DatabaseDAO<T extends Model> implements DAO<T>{
    protected final Connection conn = DatabaseConnection.getConnection();

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
    protected abstract String getTableName();

    protected T getBy(String columnName, Object value) {
        try {
            String query = "select * from " + getTableName() + " where " + columnName + " = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setObject(1, value);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next())
                return null;

            return mapResultSetToEntity(rs);
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar por " + columnName + ".", e);
        }
    }

	protected List<T> getAllBy(String columnName, Object value) {
		try {
            String query = "select * from " + getTableName() + " where " + columnName  + " = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            pstm.setObject(1, value);

            ResultSet rs = pstm.executeQuery();
			List<T> entities = new ArrayList<>();

            while (rs.next())
                entities.add(mapResultSetToEntity(rs));

            return entities;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os registros.", e);
        }
	}

    protected void deleteBy(String columnName, Object value) {
        try {
            String query = "delete from " + getTableName() + " where " + columnName + " = ?;";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setObject(1, value);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar registro.", e);
        }
    }

    @Override
    public T getById(int id) {
        return this.getBy("id", id);
    }

    @Override
    public List<T> getAll() {
        try {
            String query = "select * from " + getTableName() + ";";
            PreparedStatement pstm = this.conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            List<T> entities = new ArrayList<>();

            while (rs.next())
                entities.add(mapResultSetToEntity(rs));

            return entities;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os registros.", e);
        }
    }

    @Override
    public void save(T entity) {
        try {
            Class<?> clazz = entity.getClass();
            Field[] f = clazz.getDeclaredFields();
            Field[] fields = Arrays.copyOfRange(f, 1, f.length);

            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();

            for (Field field : fields) {
                columns.append(field.getName()).append(", ");
                placeholders.append("?, ");
            }

            columns.setLength(columns.length() - 2);
            placeholders.setLength(placeholders.length() - 2);

            String query = "insert into " + getTableName() + " (" + columns + ") values (" + placeholders + ");";
            PreparedStatement pstm = this.conn.prepareStatement(query);

            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                pstm.setObject(index++, value);
            }

            pstm.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new DatabaseException("Erro ao salvar registro.", e);
        }
    }

    @Override
    public void update(T entity) {
        try {
            Class<?> clazz = entity.getClass();
            Field[] f = clazz.getDeclaredFields();
            Field[] fields = Arrays.copyOfRange(f, 1, f.length);

            StringBuilder columnsAndPlaceholders = new StringBuilder();

            for (Field field : fields)
                columnsAndPlaceholders.append(field.getName()).append(" = ?, ");
            columnsAndPlaceholders.setLength(columnsAndPlaceholders.length() - 2);

            String query = "update " + getTableName() + " set " + columnsAndPlaceholders + " where id = ?;";
            PreparedStatement pstm = this.conn.prepareStatement(query);

            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                pstm.setObject(index++, value);
            }
            pstm.setInt(index, entity.getId());

            pstm.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new DatabaseException("Erro ao atualizar registro.", e);
        }
    }

    @Override
    public void delete(int id) {
        this.deleteBy("id", id);
    }

    public int countAll() {
        try {
            String query = "select count(*) as total from " + getTableName() + ";";
            PreparedStatement pstm = this.conn.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            if (rs.next())
                return rs.getInt("total");
            return 0;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao contar registros.", e);
        }
    }
}
