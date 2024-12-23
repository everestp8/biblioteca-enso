package org.everestp.daos;

import org.everestp.models.Usuario;

public class UsuarioDAO extends InMemoryDAO<Usuario> {
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
}
