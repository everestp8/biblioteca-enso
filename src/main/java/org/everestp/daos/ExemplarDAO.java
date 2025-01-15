package org.everestp.daos;

import org.everestp.models.Exemplar;

import java.util.ArrayList;
import java.util.List;

public class ExemplarDAO extends InMemoryDAO<Exemplar> {
    public Exemplar getByIdFisico(String idFisico) {
        for (Exemplar e : this.getAll())
            if (e.getIdFisico().equals(idFisico))
                return e;
        return null;
    }

    public List<Exemplar> getAllByLivroFk(int livroFk) {
        List<Exemplar> exemplares = new ArrayList<>();
        for (Exemplar e : this.getAll())
            if (e.getLivroFk() == livroFk)
                exemplares.add(e);
        return exemplares;
    }

    public void deleteByIdFisico(String idFisico) {
        int idx = 0;
        for (Exemplar e : this.getAll()) {
            if (e.getIdFisico().equals(idFisico))
                break;
            idx++;
        }
        this.getAll().remove(idx);
    }
}
