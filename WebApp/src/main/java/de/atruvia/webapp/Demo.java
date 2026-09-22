package de.atruvia.webapp;


import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {


    private final PersonenRepository repo;


    @PostConstruct
    public void init() {
        var liste = repo.findByVorname("John");
        liste.forEach(System.out::println);

    }


}
