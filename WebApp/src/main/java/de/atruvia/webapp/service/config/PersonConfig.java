package de.atruvia.webapp.service.config;


import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.service.PersonenService;
import de.atruvia.webapp.service.internal.PersonenServiceImpl;
import de.atruvia.webapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
public class PersonConfig {

   @Bean
   @Scope("singleton")
   @Lazy
   //@Primary
   @Qualifier("antipathen")
    public List<String> createAntipathen() {
        System.out.println("Create Antipath fired");
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> createFruits() {

        return List.of("Cherry", "Banana", "Strawberry", "Apple");
    }

    //@Bean
    /*public PersonenService createPersonservice(final PersonenRepository repo, final PersonMapper mapper,@Qualifier("antipathen") final List<String> antipath ) {
       return new PersonenServiceImpl(repo, mapper, antipath);
    }*/
}
