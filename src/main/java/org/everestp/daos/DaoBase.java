package org.everestp.daos;

import org.everestp.models.Model;

import java.util.ArrayList;
import java.util.List;

public class DaoBase<T extends Model> {
    private List<T> objects = new ArrayList<>();
    public T getById(int id) {
        for (T t : this.objects)
            if (t.getId() == id)
                return t;
        return null;
    }
    public List<T> getAll() {
        return this.objects;
    }
    public void save(T t) {
        this.objects.add(t);
    }
    public void update(T newT) {
        int idx = 0;
        for (T t : this.objects) {
            if (t.getId() == newT.getId())
                this.objects.set(idx, newT);
            idx++;
        }
    }
    public void delete(int id)  {
        int idx = 0;
        for (T t : this.objects) {
            if (t.getId() == id)
                this.objects.remove(idx);
            idx++;
        }
    }
}
