package com.minkov;

import com.minkov.entities.Category;
import com.minkov.entities.Product;
import com.minkov.repositories.CategoriesRepository;
import com.minkov.repositories.ProductsRepository;
import com.minkov.repositories.base.RepositoryBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String args[]) throws Exception {
        Connection connection = getConnection();

        RepositoryBase<Category> categories = new CategoriesRepository(connection);

        System.out.println(categories.getById(1));

        RepositoryBase<Product> products = new ProductsRepository(connection);

        Product product = new Product("Luk", 0.5f, 1);

        product = products.create(product);

        System.out.println(product.toString());

        products.getAll()
            .forEach(System.out::println);

        connection.close();
    }

    static Connection getConnection() throws Exception {
        // test if jdbc driver is available
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/onlinemarketdb", "root", "");
        return con;
    }
}  