package sample.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.entities.Actor;
 
import sample.repository.ActorJpaRepository;
 
@RestController
public class ActorController {

 

    @Autowired
    ActorJpaRepository jpa;

    
    @GetMapping("/modernJpa")
    public List<Actor> jpa() throws SQLException {
        return jpa.getAll();
    }
}
