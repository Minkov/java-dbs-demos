package com.minkov.repositories.base;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryBase<T> {
    List<T> getAll() throws SQLException;

    T create(T entity) throws SQLException;

    T getById(int id) throws SQLException;
}
