package de.atruvia.webapp.events;

import de.atruvia.webapp.service.model.Person;

import java.util.UUID;

public record PersonDeletedEvent(UUID id) {
}
