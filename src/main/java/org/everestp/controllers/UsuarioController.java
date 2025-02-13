package org.everestp.controllers;

import org.everestp.dtos.UsuarioDTO;
import org.everestp.models.Usuario;
import org.everestp.services.EmprestimoService;
import org.everestp.services.UsuarioService;

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public UsuarioController(UsuarioService usuarioService, EmprestimoService emprestimoService) {
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
    }

    public int autenticarUsuario(String email, String senha) {
        return usuarioService.autenticarUsuario(email, senha);
    }

    public Usuario fazerLogin(String email, String senha) {
        int code = usuarioService.autenticarUsuario(email, senha);
        if (code == 0) {
            return usuarioService.getUserByEmail(email);
        }
        return null;
    }

    public Usuario cadastrarUsuario(String email, String senha, String cpf, int papel) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(email, senha, cpf, papel);
        return usuarioService.cadastrarUsuario(usuarioDTO);
    }

    public int excluirUsuario(int usuarioId) {
        if (!emprestimoService.getAllEmprestimosByUsuarioId(usuarioId).isEmpty()) {
            return 1;
        }
        return usuarioService.excluirUsuario(usuarioId);
    }

    public int alterarDadosUsuario(int usuarioId, String email, String senha, String cpf) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                email.equals(".") ? null : email,
                senha.equals(".") ? null : senha,
                cpf.equals(".") ? null : cpf,
                null
        );
        return usuarioService.atualizarUsuario(usuarioId, usuarioDTO);
    }
}
