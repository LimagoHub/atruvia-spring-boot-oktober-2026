package de.atruvia.oniondemo.infrastructur.adapter.event.schwein;


import de.atruvia.oniondemo.domain.schwein.event.ScheinDeletedEvent;
import de.atruvia.oniondemo.domain.schwein.event.SchweinCreatedEvent;
import de.atruvia.oniondemo.domain.schwein.event.SchweinMaxWeightReachedEvent;
import de.atruvia.oniondemo.domain.schwein.event.SchweinUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Driven Adapter: veröffentlicht Schwein-Domain-Events auf dedizierten Kafka-Topics.
 *
 * SchweinUpdatedEvent ist ein internes Ereignis (Umbenennung/Gewichtsänderung) —
 * nur geloggt, kein Kafka. SchweinMaxWeightReachedEvent ist fachlich relevant
 * für nachgelagerte Systeme → Kafka.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SchweinEventPublisher {



    @EventListener
    public void on(SchweinCreatedEvent e) {
        log.warn("[EVENT] SchweinCreatedEvent | eventId: {} | Schwein '{}' angelegt | Gewicht: {} kg",
                e.eventId(), e.name(), e.gewicht());

    }


    @EventListener
    public void on(SchweinUpdatedEvent e) {
        log.warn("[EVENT] SchweinUpdatedEvent | eventId: {} | Schwein '{}' | Gewicht: {} kg",
                e.eventId(), e.name(), e.gewicht());
    }


    @EventListener
    public void on(SchweinMaxWeightReachedEvent e) {
        log.warn("[EVENT] SchweinMaxWeightReachedEvent | eventId: {} | Schwein '{}' hat Maximalgewicht {} kg erreicht!",
                e.eventId(), e.name(), e.gewicht());

    }


    @EventListener
    public void on(ScheinDeletedEvent e) {
        log.info("[EVENT] ScheinDeletedEvent | eventId: {} | Schwein '{}' gelöscht",
                e.eventId(), e.name());

    }
}
