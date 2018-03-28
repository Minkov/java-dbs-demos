package com.minkov.repositories;

import com.minkov.entities.Product;
import com.minkov.repositories.base.RepositoryBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepository implements RepositoryBase<Product> {

    private Connection connection;

    public ProductsRepository(Connection connection) {
        setConnection(connection);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();

        String queryString = "" +
            "   SELECT products.id," +
            "   products.name Product,\n" +
            "   products.price AS Price,\n" +
            "   products.quantity AS Quantity,\n" +
            "   categories.name AS Category\n" +
            "FROM products\n" +
            "LEFT JOIN products_categories\n" +
            "ON products.id = products_categories.product_id\n" +
            "LEFT JOIN categories\n" +
            "ON categories.id = products_categories.category_id;";

        Statement query = getConnection().createStatement();
        ResultSet rs = query.executeQuery(queryString);

        Product product = null;

        while (rs.next()) {
            int id = rs.getInt(1);
            String productName = rs.getString(2);
            float price = rs.getFloat(3);
            int quantity = rs.getInt(4);
            String categoryName = rs.getString(5);

            if (product == null || product.getId() != id) {
                product = new Product(id, productName, price, quantity);
                products.add(product);
            }

            product.addCategory(categoryName);
        }

        return products;
    }

    @Override
    public Product getById(int id) throws SQLException {
        String queryString = "SELECT name, price, quantity\n" +
            "FROM products\n" +
            "WHERE id = " + id;

        Statement query = getConnection().createStatement();

        ResultSet result = query.executeQuery(queryString);

        result.next();

        String name = result.getString(1);
        float price = result.getFloat(2);
        int quantity = result.getInt(3);

        return new Product(id, name, price, quantity);
    }

    @Override
    public Product create(Product product) throws SQLException {
        String queryString =
            "INSERT INTO products (name, price, quantity)\n" +
                "VALUES (?, ?, ?);";

        PreparedStatement query = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

        query.setString(1, product.getName());
        query.setFloat(2, product.getPrice());
        query.setInt(3, product.getQuantity());

        query.executeUpdate();

        ResultSet idsResultSet = query.getGeneratedKeys();

        idsResultSet.next();
        int id = idsResultSet.getInt(1);

        return getById(id);
    }



    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
