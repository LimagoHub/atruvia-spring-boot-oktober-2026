package de.atruvia.webapp.events;

import de.atruvia.webapp.service.model.Person;

public record PersonUpdatedEvent(Person person) {
}
