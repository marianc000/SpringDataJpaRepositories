package sample.repository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import static sample.Stats.printStats;
import static sample.repository.ActorJpaRepositoryTest.os;
import static sample.repository.ActorJpaRepositoryTest.out;
import static sample.repository.ActorJpaRepositoryTest.print;

@TestMethodOrder(MethodName.class)
@SpringBootTest
@Transactional
public class ActorRepositoryTest {

    @Autowired
    ActorRepository db;

    @Autowired
    EntityManager em;

    static PrintStream out = System.out;
    static ByteArrayOutputStream os = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(os));
    }

    @BeforeEach
    void beforeEach() {
        em.clear();
    }

    static void print() {
        Map<Boolean, List<String>> m = os.toString().lines().filter(l->!l.contains("bind => ["))
                .collect(Collectors.partitioningBy(s -> s.startsWith("[EL Fine]: sql: SELECT")));
        out.println(String.join("\n", m.get(false)));
        out.println(m.get(true).size() + " queries");
        os.reset();
    }

    @AfterEach
    void afterEach() {
        print();
    }

    @AfterAll
    static void afterAll() {
        print();
        System.setOut(out);
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
}
