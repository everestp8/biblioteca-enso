package org.everestp.services;

import org.everestp.daos.UsuarioDAO;
import org.everestp.dtos.UsuarioDTO;
import org.everestp.exceptions.DadosInvalidosException;
import org.everestp.exceptions.SenhaIncorretaException;
import org.everestp.exceptions.UsuarioNaoEncontradoException;
import org.everestp.models.Usuario;
import org.everestp.utils.Validator;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario getUserByEmail(String email) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        if (usuario == null)
            throw new UsuarioNaoEncontradoException();
        return usuario;
    }

    public void atualizarUsuario(int usuarioId, UsuarioDTO dadosAtualizados) {
        if (!Validator.validarUsuarioDTO(dadosAtualizados))
            throw new DadosInvalidosException("Dados inválidos para livro.");
        Usuario usuarioExistente = this.usuarioDAO.getById(usuarioId);
        Usuario usuarioAtualizado;
        if (usuarioExistente == null)
            throw new UsuarioNaoEncontradoException();

        String novoNome = dadosAtualizados.nome() != null ? dadosAtualizados.nome() : usuarioExistente.getNome();
        String novoEmail = dadosAtualizados.email() != null ? dadosAtualizados.email() : usuarioExistente.getEmail();
        String novaSenha = dadosAtualizados.senha() != null ? dadosAtualizados.senha() : usuarioExistente.getSenha();
        String novoCpf = dadosAtualizados.cpf() != null ? dadosAtualizados.cpf() : usuarioExistente.getCpf();
        Integer novoPapel = dadosAtualizados.papel() != null ? dadosAtualizados.papel() : usuarioExistente.getPapel();
        usuarioAtualizado = new Usuario(usuarioExistente.getId(), novoNome, novoEmail, novaSenha, novoCpf, novoPapel);

        this.usuarioDAO.update(usuarioAtualizado);
    }

    public Usuario cadastrarUsuario(UsuarioDTO dados) {
        if (!Validator.validarUsuarioDTO(dados))
            throw new DadosInvalidosException("Dados inválidos para usuário.");
        Usuario usuario = new Usuario(0, dados.nome(), dados.email(), dados.senha(), dados.cpf(), dados.papel());
        this.usuarioDAO.save(usuario);
        return this.usuarioDAO.getByEmail(dados.email());
    }

    public void autenticarUsuario(String email, String senha) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }
        if (!usuario.getSenha().equals(senha)) {
            throw new SenhaIncorretaException();
        }
    }

    public void excluirUsuario(int usuarioId) {
        Usuario usuario = this.usuarioDAO.getById(usuarioId);
        if (usuario == null)
            throw new UsuarioNaoEncontradoException();
        this.usuarioDAO.delete(usuarioId);
    }

	public void deleteUsuarioByEmail(String email) {
		Usuario usuario = this.usuarioDAO.getByEmail(email);
        if (usuario == null)
            throw new UsuarioNaoEncontradoException();
		this.usuarioDAO.delete(usuario.getId());
	}

    public int countUsuarios() {
        return this.usuarioDAO.countAll();
    }
}
