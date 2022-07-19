package sample.entities;

import java.util.LinkedHashSet;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Film {

    @Id
    int filmId;
    String title;

    @ManyToMany
    @JoinTable(name = "FILM_CATEGORY", inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"),joinColumns = @JoinColumn(name = "FILM_ID"))
    Set<Category> categories = new LinkedHashSet<>();
 
    public Film() {
    }

    public Film(int filmId, String title) {
        this.filmId = filmId;
        this.title = title;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public Set<Category> getCategories() {
        return categories;
    }

//    @Override
//    public String toString() {
//        return "{id:" + id + ", name: '" + name
//                + "', categories: [" + categories.stream().map(a -> a.toString()).collect(Collectors.joining(",")) + "]}";
//    }
}
