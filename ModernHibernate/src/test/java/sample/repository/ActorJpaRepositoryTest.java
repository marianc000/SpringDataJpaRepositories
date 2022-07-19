package sample.repository;

 
import sample.entities.Actor;
import java.util.List;
//import javax.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodName.class)
@SpringBootTest
public class ActorJpaRepositoryTest {
     
    @Autowired
    ActorJpaRepository jpa;

    @Test
    void testgetAll() {
        List<Actor> actors = jpa.getAll();
        long filmCnt = actors.stream().flatMap(a -> a.getFilms().stream()).count();
        long categoriesCnt = actors.stream().flatMap(a -> a.getFilms().stream()).flatMap(f -> f.getCategories().stream()).count();
        actors.stream().flatMap(a -> a.getFilms().stream()).flatMap(f -> f.getCategories().stream()).forEach(c -> c.toString());

        System.out.println("Found actors " + actors.size()
                + ", films " + filmCnt + ", categories " + categoriesCnt);
        
       
    }
}
