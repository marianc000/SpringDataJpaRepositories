package sample.jdbc;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import org.springframework.test.context.ContextConfiguration;
import static sample.Stats.printStats;
import sample.entities.Actor;
 

@JdbcTest
@ContextConfiguration(classes = sample.jdbc.ActorDao.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ActorDaoTest {

    @Autowired
    ActorDao dao;
 
    @Test
    public void testGetAll() throws SQLException {
        List<Actor> l = dao.getAll();
         printStats(l);
    }
}
