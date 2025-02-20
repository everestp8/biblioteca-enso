package org.everestp.controllers;

import org.everestp.dtos.UsuarioDTO;
import org.everestp.exceptions.EmprestimosPendentesException;
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

    public Response<Usuario> fazerLogin(String email, String senha) {
        try {
            this.usuarioService.autenticarUsuario(email, senha);
            Usuario usuario = this.usuarioService.getUserByEmail(email);
            return Response.sucesso(usuario);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Usuario> cadastrarUsuario(UsuarioDTO dadosUsuario) {
        try {
            Usuario usuario = this.usuarioService.cadastrarUsuario(dadosUsuario);
            return Response.sucesso(usuario);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Void> excluirUsuario(int usuarioId) {
        try {
            if (!this.emprestimoService.getAllEmprestimosByUsuarioId(usuarioId).isEmpty()) {
                throw new EmprestimosPendentesException();
            }
            this.usuarioService.excluirUsuario(usuarioId);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }

    }

	public Response<Void> excluirUsuarioPorEmail(String email) {
        try {
            this.usuarioService.deleteUsuarioByEmail(email);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }

    }

    public Response<Void> alterarDadosUsuario(int usuarioId, UsuarioDTO dadosUsuario) {
        try {
            this.usuarioService.atualizarUsuario(usuarioId, dadosUsuario);
            return Response.sucesso();
        } catch (Exception e) {
            return Response.falha(e);
        }
    }

    public Response<Integer> contarUsuarios() {
        try {
            int quantUsuarios = this.usuarioService.countUsuarios();
            return Response.sucesso(quantUsuarios);
        } catch (Exception e) {
            return Response.falha(e);
        }
    }
}
