package org.everestp.daos;

import org.everestp.models.Usuario;

public class UsuarioDAO extends InMemoryDAO<Usuario> {
    public Usuario getByEmail(String email) {
        for (Usuario u : this.getAll())
            if (u.getEmail().equalsIgnoreCase(email))
                return u;
        return null;
    }
}
