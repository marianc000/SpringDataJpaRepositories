package sample.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.entities.Actor;
import sample.jdbc.ActorDao;
import sample.repository.ActorJpaRepository;
import sample.repository.ActorRepository;

@RestController
public class ActorController {

    @Autowired
    ActorRepository rep;

    @Autowired
    ActorDao jdbc;

    @Autowired
    ActorJpaRepository jpa;

    @GetMapping("/findAll")
    public List<Actor> findAll() {
        return rep.findAll();
    }

    @GetMapping("/findAllSorted")
    public List<Actor> findAllSorted() {
        return rep.findAll(Sort.by("lastName", "firstName", "films.title", "films.categories.name"));
    }

    @GetMapping("/query")
    public List<Actor> query() {
        return rep.query();
    }

    @GetMapping("/jdbc")
    public List<Actor> jdbc() throws SQLException {
        return jdbc.getAll();
    }

    @GetMapping("/jpa")
    public List<Actor> jpa() throws SQLException {
        return jpa.getAll();
    }
}
