package com.minkov.repositories;

import com.minkov.repositories.base.RepositoryBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntitiesRepository<T> implements RepositoryBase<T> {

    private Connection connection;

    public EntitiesRepository(Connection connection) {
        setConnection(connection);
    }

    @Override
    public List<T> getAll() throws SQLException {
        return null;
    }

    @Override
    public T create(T entity) throws SQLException {
        return null;
    }

    @Override
    public T getById(int id) throws SQLException {
        List<String> columnNames = getColumnNames();

        String columnNamesString =
            columnNames.stream()
                .collect(Collectors.joining(", "));

        String tableName = getTableName();

        String queryString = "SELECT " + columnNamesString + "\n" +
            "FROM " + tableName + "\n" +
            "WHERE id = " + id;

        Statement query = getConnection().createStatement();
        ResultSet resultSet = query.executeQuery(queryString);

        return loadFromResultSet(resultSet);
    }

    protected abstract T loadFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract String getTableName();

    protected abstract List<String> getColumnNames();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
