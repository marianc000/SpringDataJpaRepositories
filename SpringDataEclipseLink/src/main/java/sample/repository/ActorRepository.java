package sample.repository;

import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import sample.entities.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
 
    
    @Query("SELECT a FROM Actor a LEFT JOIN a.films f LEFT JOIN f.categories c"
            + " order by a.lastName,a.firstName,f.title,c.name")
    @QueryHints({
        @QueryHint(name = "eclipselink.left-join-fetch", value = "a.films.categories")})
    List<Actor> query();

    List<Actor> findByFirstName(String name);

}
 