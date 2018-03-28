package com.minkov.repositories.base;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryBase<T> {
    List<T> getAll() throws Exception;

    T create(T entity) throws Exception;

    T getById(int id) throws Exception;
}
