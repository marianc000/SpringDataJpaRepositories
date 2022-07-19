package sample.entities;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    Set<Category> categories ;

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

    public Set<Category> getCategories() {
        return categories;
    }

}
