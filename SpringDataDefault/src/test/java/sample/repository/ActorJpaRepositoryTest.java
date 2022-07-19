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

import static sample.Stats.printStats;

@TestMethodOrder(MethodName.class)
@SpringBootTest
@Transactional
public class ActorJpaRepositoryTest {

    @Autowired
    ActorJpaRepository db;

    @Autowired
    EntityManager em;

    static PrintStream out = System.out;
    static ByteArrayOutputStream os  ;

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
    void testgetAllJpa() {
        List l = db.getAll();
        printStats(l);
    }
}
