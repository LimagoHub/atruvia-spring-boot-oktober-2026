package de.atruvia.oniondemo.domain.schwein.event;

import de.atruvia.oniondemo.domain.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record SchweinUpdatedEvent(UUID personId,
                                  String name,
                                  int gewicht,
                                  UUID eventId,
                                  Instant occurredAt)implements DomainEvent {
    public static SchweinUpdatedEvent of(UUID personId, String name, int gewicht) {
        return new SchweinUpdatedEvent(personId, name, gewicht, UUID.randomUUID(), Instant.now());
    }
}
