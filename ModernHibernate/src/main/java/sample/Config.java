package sample;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

@Configuration
 
public class Config {
    @Bean
    public EntityManager em() {
       return Persistence.createEntityManagerFactory("H_PU") .createEntityManager();
    }
}