package com.minkov.repositories;

import com.minkov.repositories.base.RepositoryBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntitiesRepository<T> implements RepositoryBase<T> {
    private Connection connection;

    public EntitiesRepository(Connection connection) {
        setConnection(connection);
    }

    public List<T> getAll() throws Exception {
        List<T> entities = new ArrayList<>();

        Statement query = getConnection().createStatement();
        ResultSet rs = query.executeQuery(getGetAllQuery());

        while (true) {
            T entity = loadDetailedFromResultSet(rs);
            if (entity == null) {
                break;
            }
            entities.add(entity);
        }

        return entities;
    }


    @Override
    public T getById(int id) throws Exception {
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

        return loadBasicFromResultSet(resultSet);
    }

    @Override
    public T create(T entity) throws Exception {
        List<String> columnNames = new ArrayList<>(getColumnNames());

        if (columnNames.contains("id")) {
            columnNames.remove("id");
        }

        String columnNamesString = columnNames
            .stream()
            .collect(Collectors.joining(", "));
        String queryString =
            "INSERT INTO products (" + columnNamesString + ")\n" +
                "VALUES (?, ?, ?);";

        PreparedStatement query = getConnection().prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
        prepareInsertStatement(query, entity);

        query.executeUpdate();

        ResultSet idsResultSet = query.getGeneratedKeys();

        idsResultSet.next();
        int id = idsResultSet.getInt(1);

        return getById(id);
    }

    protected String getGetAllQuery() {
        String columnNamesString = getColumnNames()
            .stream()
            .collect(Collectors.joining(", "));

        return "SELECT " + columnNamesString + "\n" +
            "FROM " + getTableName();
    }

    protected abstract void prepareInsertStatement(PreparedStatement query, T entity) throws SQLException;

    protected abstract T loadBasicFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract T loadDetailedFromResultSet(ResultSet rs) throws SQLException;

    protected abstract String getTableName();

    protected abstract List<String> getColumnNames();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
