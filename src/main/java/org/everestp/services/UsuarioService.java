package org.everestp.services;

import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.UsuarioDTO;
import org.everestp.models.Usuario;

import javax.swing.*;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    public void cadastrarUsuario(UsuarioDTO dados) {
        Usuario usuario = new Usuario(0, dados.email(), dados.senha(), dados.cpf(), dados.papel());
        this.usuarioDAO.save(usuario);
    }

    public int autenticarUsuario(String email, String senha) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        if (usuario == null)
            return 1;
        if (!usuario.getSenha().equals(senha))
            return 2;
        return 0;
    }
}
