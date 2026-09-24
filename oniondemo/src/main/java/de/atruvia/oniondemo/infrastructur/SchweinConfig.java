package de.atruvia.oniondemo.infrastructur;


import de.atruvia.oniondemo.application.port.outport.SchweinRepository;
import de.atruvia.oniondemo.application.service.SchweineService;
import de.atruvia.oniondemo.application.service.internal.SchweinServiceImpl;
import de.atruvia.oniondemo.application.shared.DomainEventPublisher;
import de.atruvia.oniondemo.infrastructur.adapter.repository.mapper.SchweinDocumentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.swing.event.DocumentEvent;

@Configuration
public class SchweinConfig {

    @Bean
    public SchweineService createSchweinSchweinService(SchweinRepository repo, DomainEventPublisher eventPublisher) {
        return new SchweinServiceImpl(repo, eventPublisher);
    }
}
