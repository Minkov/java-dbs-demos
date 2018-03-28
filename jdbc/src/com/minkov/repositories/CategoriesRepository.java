package com.minkov.repositories;

import com.minkov.entities.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoriesRepository extends EntitiesRepository<Category> {

    public CategoriesRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected Category loadFromResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Category(id, name);
    }

    @Override
    protected String getTableName() {
        return "categories";
    }

    @Override
    protected List<String> getColumnNames() {
        return List.of("id", "name");
    }
}
