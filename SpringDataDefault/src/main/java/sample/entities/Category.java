package sample.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    int categoryId;
    String name;

    public Category() {
    }

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

//    @Override
//    public String toString() {
//        return "{id:" + id + ", name:'" + name + "'}";
//    }
}
