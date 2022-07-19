package sample.repository;

import sample.entities.Actor;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActorJpaRepository {

    @Autowired
    private EntityManager em;

    public List<Actor> getAll() {
        return em.createQuery("SELECT a FROM Actor a LEFT JOIN FETCH a.films f LEFT JOIN FETCH f.categories c"
                + " order by a.lastName,a.firstName,f.title,c.name", Actor.class).getResultList();
    }
}
