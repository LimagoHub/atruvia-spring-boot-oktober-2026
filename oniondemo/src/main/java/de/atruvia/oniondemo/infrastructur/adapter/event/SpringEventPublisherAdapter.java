package de.atruvia.oniondemo.infrastructur.adapter.event;


import de.atruvia.oniondemo.application.shared.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Driven Adapter: implementiert DomainEventPublisher.
 *
 * Leitet Domain-Events direkt an Springs ApplicationEventPublisher weiter.
 * Die fachliche Verarbeitung (Logging, Kafka) liegt in den aggregat-spezifischen
 * Publishern: PersonEventPublisher, BankAccountEventPublisher.
 */
@Component
@RequiredArgsConstructor
public class SpringEventPublisherAdapter implements DomainEventPublisher {

    private final ApplicationEventPublisher springEventPublisher;

    @Override
    public void publish(List<Object> events) {

        events.forEach(springEventPublisher::publishEvent);

    }
}
