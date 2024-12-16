package org.everestp.daos;

import org.everestp.models.Usuario;

import java.util.List;

public class UsuarioDAO extends DaoBase<Usuario> {
    public Usuario getByEmail(String email) {
        for (Usuario u : this.getAll())
            if (u.getEmail().equalsIgnoreCase(email))
                return u;
        return null;
    }
}
