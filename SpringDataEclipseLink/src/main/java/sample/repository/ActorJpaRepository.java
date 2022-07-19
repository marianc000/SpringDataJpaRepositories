package sample.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sample.entities.Actor;

@Repository
public class ActorJpaRepository {

    @Autowired
    EntityManager em;

    public List<Actor> getAll () {
        return em.createQuery("SELECT a FROM Actor a LEFT JOIN a.films f LEFT JOIN f.categories c"
                + " order by a.lastName,a.firstName,f.title,c.name", Actor.class)
                .setHint("eclipselink.left-join-fetch", "a.films.categories")
                .getResultList();
    }
}
