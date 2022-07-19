package sample.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sample.entities.Actor;
import sample.entities.Category;
import sample.entities.Film;
import static sample.jdbc.collectors.GroupingBy.groupingBy;

@Repository
public class ActorDao {

    @Autowired
    DataSource ds;

    public List<Actor> getAll() throws SQLException {
        return group(loadActors("select a.actor_id, a.first_name,a.last_name, f.film_id, f.title, "
                + "c.category_id, c.name from actor a "
                + "left join film_actor using(actor_id) "
                + "left join film f using(film_id) "
                + "left join film_category using(film_id) "
                + "left join category c using(category_id) "
                + "order by a.last_name,a.first_name, f.title, c.name"));
    }

    List<Actor> loadActors(String sql) throws SQLException {
        try ( Connection con = ds.getConnection();  Statement st = con.createStatement();  ResultSet rs = st.executeQuery(sql)) {
            List<Actor> l = new LinkedList<>();
            rs.setFetchSize(10000);
            while (rs.next()) {
                Actor c = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
                Film a = new Film(rs.getInt(4), rs.getString(5));
                c.getFilms().add(a);
                a.getCategories().add(new Category(rs.getInt(6), rs.getString(7)));
                l.add(c);
            }
            return l;
        }
    }

    List<Actor> group(List<Actor> l) {
        l = l.stream()
                .collect(groupingBy(a -> a.getActorId(), a -> a.getFilms()));

        l.forEach(a -> {
            List<Film> l2 = a.getFilms().stream().
                    collect(groupingBy(f -> f.getFilmId(), f -> f.getCategories()));
            a.setFilms(l2);
        });

        return l;
    }
}
