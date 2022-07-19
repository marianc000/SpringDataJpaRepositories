package sample.entities;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.Column;

@Entity
public class Actor {

    @Id
    @Column(name = "ACTOR_ID")
    Integer actorId;
    @Column(name = "FIRST_NAME")
    String firstName;
    @Column(name = "LAST_NAME")
    String lastName;

    @ManyToMany
    @JoinTable(name = "FILM_ACTOR", inverseJoinColumns = @JoinColumn(name = "FILM_ID"), joinColumns = @JoinColumn(name = "ACTOR_ID"))
    List<Film> films = new LinkedList<>();

    public Actor() {
    }

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getActorId() {
        return actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

}
