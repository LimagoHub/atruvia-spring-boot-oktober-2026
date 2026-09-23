package de.atruvia.webapp;


import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.service.MailServiceDummy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {


  private final MailServiceDummy mailServiceDummy;


    @PostConstruct
    public void init() {
        System.out.println(mailServiceDummy);
        mailServiceDummy.send("a","b");
    }


}
