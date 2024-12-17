package org.everestp.services;

import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.UsuarioDTO;
import org.everestp.models.Usuario;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario getUsuario(String email) {
        return this.usuarioDAO.getByEmail(email);
    }

    public Usuario cadastrarUsuario(UsuarioDTO dados) {
        Usuario usuario = new Usuario(0, dados.email(), dados.senha(), dados.cpf(), dados.papel());
        this.usuarioDAO.save(usuario);
        return usuario;
    }

    public int autenticarUsuario(String email, String senha) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        if (usuario == null) {
            return 1;
        }
        if (!usuario.getSenha().equals(senha)) {
            return 2;
        }
        return 0;
    }

    public int excluirUsuario(int usuarioId) {
        this.usuarioDAO.delete(usuarioId);
        return 0;
    }
}
