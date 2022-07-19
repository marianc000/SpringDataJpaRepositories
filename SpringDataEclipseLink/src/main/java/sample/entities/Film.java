package sample.entities;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity

public class Film {

    @Id
    @Column(name = "FILM_ID")
    Integer filmId;
    String title;

    @ManyToMany
    @JoinTable(name = "FILM_CATEGORY", inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"), joinColumns = @JoinColumn(name = "FILM_ID"))
    List<Category> categories = new LinkedList<>();

    public Film() {
    }

    public Film(int filmId, String title) {
        this.filmId = filmId;
        this.title = title;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public List<Category> getCategories() {
        return categories;
    }

}
