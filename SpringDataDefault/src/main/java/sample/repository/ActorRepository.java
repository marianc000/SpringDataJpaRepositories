package sample.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sample.entities.Actor;
 

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query("SELECT a FROM Actor a LEFT JOIN FETCH a.films f LEFT JOIN FETCH f.categories c"
            + " order by a.lastName,a.firstName,f.title,c.name")
    List<Actor> query();

    @Query("SELECT distinct a FROM Actor a LEFT JOIN FETCH a.films f LEFT JOIN FETCH f.categories c"
            + " order by a.lastName,a.firstName,f.title,c.name")
    List<Actor> queryDistinct();
 
    List<Actor> findByFirstName(String name);
 
}
