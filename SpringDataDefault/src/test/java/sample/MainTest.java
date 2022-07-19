package sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Order;

@Order(1)
@SpringBootTest
class MainTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void databaseFilled() throws SQLException {
        try ( Statement st = dataSource.getConnection().createStatement();  ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM ACTOR")) {

            rs.next();
            System.out.println(rs.getInt(1));
            assertEquals(200, rs.getInt(1));

        }
    }
}
