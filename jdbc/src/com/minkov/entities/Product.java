package com.minkov.entities;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
    int id;
    String name;
    float price;
    int quantity;
    List<String> categories;

    public Product(String name, float price, int quantity) {
        this(-1, name, price, quantity);
    }

    public Product(int id, String name, float price, int quantity) {
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategories(new ArrayList<String>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
            "({0}, {1}, {2}, {3}, {4})",
            getId(),
            getName(),
            getPrice(),
            getQuantity(),
            getCategories()
                .stream()
                .collect(Collectors.joining(", "))
        );
    }

    public void addCategory(String categoryName) {
        getCategories()
            .add(categoryName);
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
