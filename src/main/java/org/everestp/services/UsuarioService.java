package org.everestp.services;

import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.UsuarioDTO;
import org.everestp.models.Usuario;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario getUserByEmail(String email) {
        return this.usuarioDAO.getByEmail(email);
    }

    public Usuario getUserByCpf(String cpf) {
        return this.usuarioDAO.getByEmail(cpf);
    }

    public int atualizarUsuario(int usuarioId, UsuarioDTO dadosAtualizados) {
        Usuario usuarioExistente = this.usuarioDAO.getById(usuarioId);
        Usuario usuarioAtualizado;

        if (usuarioExistente == null) {
            return 1;
        }
        String novoEmail = dadosAtualizados.email() != null ? dadosAtualizados.email() : usuarioExistente.getEmail();
        String novaSenha = dadosAtualizados.senha() != null ? dadosAtualizados.senha() : usuarioExistente.getSenha();
        String novoCpf = dadosAtualizados.cpf() != null ? dadosAtualizados.cpf() : usuarioExistente.getCpf();
        Integer novoPapel = dadosAtualizados.papel() != null ? dadosAtualizados.papel() : usuarioExistente.getPapel();
        usuarioAtualizado = new Usuario(usuarioExistente.getId(), novoEmail, novaSenha, novoCpf, novoPapel);

        this.usuarioDAO.update(usuarioAtualizado);
        return 0;
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

    // TODO: Não permitir apagar a conta caso haja algum empréstimo pendente
    public int excluirUsuario(int usuarioId) {
        this.usuarioDAO.delete(usuarioId);
        return 0;
    }
}
