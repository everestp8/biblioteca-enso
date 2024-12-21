package org.everestp.daos;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {
    public T getById(int id);
    public List<T> getAll();
    public void save(T t);
    public void update(T newT);
    public void delete(int id);
}
