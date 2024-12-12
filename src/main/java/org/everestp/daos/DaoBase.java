package org.everestp.daos;

import java.sql.Connection;
import java.util.List;

public interface DaoBase<T> {
    public Connection conn = null;
    public T getById(int id);
    public List<T> getAll();
    public void save(T t);
    public void update(T t);
    public void delete(int id);
}
