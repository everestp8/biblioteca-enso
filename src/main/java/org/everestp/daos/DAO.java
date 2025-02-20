package org.everestp.daos;

import java.util.List;

public interface DAO<T> {
    T getById(int id);
    List<T> getAll();
    void save(T t);
    void update(T newT);
    void delete(int id);
}
