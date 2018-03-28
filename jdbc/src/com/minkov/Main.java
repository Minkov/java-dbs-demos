package com.minkov;

import com.minkov.entities.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) throws Exception {
        Connection con = getConnection();

        getAllProducts(con)
            .forEach(System.out::println);

        con.close();
    }

    static List<Product> getAllProducts(Connection con) throws Exception {
        List<Product> products = new ArrayList<>();

        Statement query = con.createStatement();
        String queryString = "" +
            "   SELECT products.id," +
            "   products.name Product,\n" +
            "   products.price AS Price,\n" +
            "   products.quantity AS Quantity,\n" +
            "   categories.name AS Category\n" +
            "FROM products\n" +
            "JOIN products_categories\n" +
            "ON products.id = products_categories.product_id\n" +
            "JOIN categories\n" +
            "ON categories.id = products_categories.category_id;";

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

    static Connection getConnection() throws Exception {
        // test if jdbc driver is available
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/onlinemarketdb", "root", "");
        return con;
    }
}  