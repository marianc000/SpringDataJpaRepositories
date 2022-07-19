package sample.entities;

import java.util.LinkedHashSet;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Actor {

    @Id
    int actorId;

    String firstName;
    String lastName;

    @ManyToMany
    @JoinTable(name = "FILM_ACTOR", inverseJoinColumns = @JoinColumn(name = "FILM_ID"), joinColumns = @JoinColumn(name = "ACTOR_ID"))
    Set<Film> films = new LinkedHashSet<>();

    public Actor() {
    }

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActorId() {
        return actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

//    @Override
//    public String toString() {
//        return  "{ id:" + id + ", name:'" + name + "', films: [" 
//                + films.stream().map(a->a.toString()).collect(Collectors.joining(",")) + "]}";
//    }
}
