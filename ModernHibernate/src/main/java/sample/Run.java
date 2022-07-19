package sample;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static java.lang.String.format;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sample.Stats.Counts;
import static sample.Stats.accessChildren;
import sample.entities.Actor;
import sample.repository.ActorJpaRepository;

 @Component
public class Run implements CommandLineRunner {

    interface ThrowingSupplier<T> {

        T get() throws Exception;
    }

    @Autowired
    private EntityManager em;

    @Autowired
    ActorJpaRepository jpa;

    static PrintStream out = System.out;

    void print(String name, Object actors, Object films, Object categories, Object queries, String vals, Object avg) {
        System.out.println(format("%-31s", name) + format("%-7s", actors) + format("%-7s", films)
                + format("%-11s", categories) + format("%-8s", queries) + format("%-55s", vals) + format("%-10s", avg));

    }

    void measure(ThrowingSupplier<List<Actor>> s, String name) throws Exception {
        LinkedList<Long> l = new LinkedList<>();
        Counts counts = null;
        long queries = 0;

        for (int i = 0; i < 15; i++) {
            em.clear();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            System.setOut(new PrintStream(os));

            long start = System.currentTimeMillis();

            counts = accessChildren(s.get());
            if (i > 4) {
                l.add(System.currentTimeMillis() - start);
            }
            System.setOut(out);

            queries = os.toString().lines().filter(str -> str.startsWith("Hibernate: select")).count();
        }
        long avg = Math.round(l.stream().collect(Collectors.averagingLong(v -> v)));

        print(name, counts.actors(), counts.films(), counts.categories(), queries, l.toString(), avg);
    }

    @Override
    public void run(String[] strings) throws Exception {
        print("Approach", "Actors", "Films", "Categories", "Queries", "Durations", "Average");

        measure(() -> jpa.getAll(), "jpa");

    }
}
