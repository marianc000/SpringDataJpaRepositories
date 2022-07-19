package sample;

import java.util.List;
import sample.entities.Actor;
import sample.entities.Category;
import sample.entities.Film;

public class Stats {

    public static record Counts(int actors, int films, int categories) {

    }

    public static Counts accessChildren(List<Actor> actors) {
        List<Film> films = actors.stream().flatMap(a -> a.getFilms().stream()).toList();
        List<Category> categories = films.stream().flatMap(f -> f.getCategories().stream()).toList();
        categories.forEach(c -> c.getName());
        return new Counts(actors.size(), films.size(), categories.size());
    }

    public static void printStats(List<Actor> actors) {
        Counts counts = accessChildren(actors);
        System.out.println("Loaded " + counts.actors + " actors, "
                + counts.films + " films, " + counts.categories + " categories");

    }
}
