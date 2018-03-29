package entities;

import javax.persistence.*;
import java.text.MessageFormat;

@Entity
@Table(name = "categories")
public class Category {
    int id;
    String name;

    public Category() {

    }

    public Category(String name) {
        this(-1, name);
    }

    public Category(int id, String name) {
        setId(id);
        setName(name);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 40)
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
