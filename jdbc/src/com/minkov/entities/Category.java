package com.minkov.entities;

import java.text.MessageFormat;

public class Category {
    int id;
    String name;

    public Category(String name) {
        this(-1, name);
    }

    public Category(int id, String name) {
        setId(id);
        setName(name);
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

    @Override
    public String toString() {
        return MessageFormat.format(
            "({0}, {1})",
            this.getId(),
            this.getName()
        );
    }
}
