package sample;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static java.lang.String.format;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import static sample.Out.getOut;
import static sample.Out.getOutput;
import static sample.Out.printOut;
import static sample.Out.restoreOut;
import sample.Stats.Counts;
import static sample.Stats.accessChildren;
import sample.entities.Actor;
import sample.jdbc.ActorDao;
import sample.repository.ActorJpaRepository;
import sample.repository.ActorRepository;

@Component
@Transactional
public class Run implements CommandLineRunner {

    interface ThrowingSupplier<T> {

        T get() throws Exception;
    }

    @Autowired
    ActorDao jdbc;

    @Autowired
    ActorRepository rep;

    @Autowired
    ActorJpaRepository jpa;

    @Autowired
    EntityManager em;

    void print(String name, Object actors, Object films, Object categories, Object queries, String vals, Object avg) {
        getOut().println(format("%-31s", name) + format("%-7s", actors) + format("%-7s", films)
                + format("%-11s", categories) + format("%-8s", queries) + format("%-55s", vals) + format("%-10s", avg));
    }

    void measure(ThrowingSupplier<List<Actor>> s, String name) throws Exception {
        LinkedList<Long> l = new LinkedList<>();
        Counts counts = null;
        long queries = 0;
       
        for (int i = 0; i < 15; i++) {
            em.clear();

            long start = System.currentTimeMillis();

            counts = accessChildren(s.get());
            if (i > 4) {
                l.add(System.currentTimeMillis() - start);
            }

            queries = getOutput().lines().filter(str -> str.startsWith("[EL Fine]: sql: SELECT")).count();

        }
        long avg = Math.round(l.stream().collect(Collectors.averagingLong(v -> v)));

        print(name, counts.actors(), counts.films(), counts.categories(), queries, l.toString(), avg);
        restoreOut();
    }

    @Override
    public void run(String[] strings) throws Exception { 
        printOut();
        print("Approach", "Actors", "Films", "Categories", "Queries", "Durations", "Average");
        measure(() -> jdbc.getAll(), "jdbc");

        measure(() -> rep.findAll(), "rep.findAll()");

        measure(() -> rep.findAll(Sort.by("lastName", "firstName", "films.title", "films.categories.name")), "rep.findAll(Sort)");

        measure(() -> jpa.getAll(), "jpa");

        measure(() -> rep.query(), "rep.query()");

        measure(() -> rep.findByFirstName("KENNETH"), "rep.findByFirstName(\"KENNETH\")");
    }
}
