package sample.repository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import sample.entities.Actor;
import static sample.Stats.printStats;

@TestMethodOrder(MethodName.class)
@SpringBootTest
@Transactional
public class ActorRepositoryTest {
//

    @Autowired
    ActorRepository db;

    @Autowired
    EntityManager em;

    static PrintStream out = System.out;
    static ByteArrayOutputStream os;

    @BeforeEach
    void beforeEach() {
        em.clear();
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
    }

    @AfterEach
    void afterEach() {

        System.setOut(out);
        Map<Boolean, List<String>> m = os.toString().lines().collect(Collectors.partitioningBy(s -> s.startsWith("Hibernate: select")));
        out.println(String.join("\n", m.get(false)));
        out.println(m.get(true).size() + " queries");
    }

    @Test
    void testFindAll() {
        List l = db.findAll();
        printStats(l);
    }

    @Test
    void testFindByName() {
        List l = db.findByFirstName("KENNETH");
        printStats(l);
    }
//

    @Test
    void testFindAllSorted() {
        List l = db.findAll(Sort.by("lastName", "films.title", "films.categories.name"));
        printStats(l);
    }

    @Test
    void testQuery() {
        List l = db.query();
        printStats(l);
    }

    @Test
    void testJpa() {
        List l = em.createQuery("SELECT a FROM Actor a LEFT JOIN FETCH a.films f LEFT JOIN FETCH f.categories c"
                + " order by a.lastName,f.title,c.name", Actor.class)
                .getResultList();
        printStats(l);
    }
//

    @Test
    void testQueryDistinct() {
        List l = db.queryDistinct();
        printStats(l);
    }
}
