package sample.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
              @Column(name="CATEGORY_ID")
   Integer categoryId;
    String name;

    public Category() {
    }

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Integer getCategoryId() {
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
