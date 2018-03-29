package repositories;

import entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CategoriesRepository extends EntitiesRepository<Category> {
    public CategoriesRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected Category loadBasicFromResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Category(id, name);
    }

    @Override
    protected Category loadDetailedFromResultSet(ResultSet rs) throws SQLException {
        return loadBasicFromResultSet(rs);
    }

    @Override
    protected String getTableName() {
        return "categories";
    }

    @Override
    protected List<String> getColumnNames() {
        return Arrays.asList("id", "name");
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement query, Category entity) throws SQLException {
        query.setString(1, entity.getName());
    }

    @Override
    public List<Category> getAll() throws Exception {
        return null;
    }
}
