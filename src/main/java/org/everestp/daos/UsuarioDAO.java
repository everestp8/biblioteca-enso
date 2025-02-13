package org.everestp.daos;

import org.everestp.DatabaseConnection;
import org.everestp.models.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    private Connection conn = DatabaseConnection.getConnection();

    public Usuario getByEmail(String email) {
        for (Usuario u : this.getAll())
            if (u.getEmail().equals(email))
                return u;
        return null;
    }
    
    public Usuario getByCpf(String cpf) {
        for (Usuario u : this.getAll())
            if (u.getCpf().equals(cpf))
                return u;
        return null;
    }

    @Override
    public Usuario getById(int id) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("select * from usuario where id = ?;");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
                return null;

            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            String cpf = rs.getString("cpf");
            LocalDate dataNascimento = rs.getDate("datanascimento").toLocalDate();
            int papel = rs.getInt("papel");

            return new Usuario(id, nome, email, senha, cpf, dataNascimento, papel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("select * from usuario;");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                LocalDate dataNascimento = rs.getDate("datanascimento").toLocalDate();
                int papel = rs.getInt("papel");

                usuarios.add(new Usuario(id, nome, email, senha, cpf, dataNascimento, papel));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return usuarios;
    }

    @Override
    public void save(Usuario usuario) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("insert into usuario(nome, email, senha, cpf, datanascimento, papel) values (?, ?, ?, ?, ?, ?);");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setDate(5, (Date) Date.from(usuario.getDtNasc().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            stmt.setInt(6, usuario.getPapel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Usuario newT) {

    }

    @Override
    public void delete(int id) {

    }
}
