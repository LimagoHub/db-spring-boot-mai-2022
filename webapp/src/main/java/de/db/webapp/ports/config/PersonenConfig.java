package de.db.webapp.ports.config;


import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class PersonenConfig {


    @Bean
    @Primary
    @Qualifier("antipathen")
    public List<String> antipathen() {
        return List.of("Attila","Peter","Paul","Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana","Cherry","Strawberry","Raspberry");
    }


    @Bean
    public PersonenService getPersonenService(PersonenRepository repo, @Qualifier("antipathen") List<String> antipathen) {
        return new PersonenServiceImpl(repo,  antipathen);
    }
}
