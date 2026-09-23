package de.atruvia.webapp.listener;


import de.atruvia.webapp.events.PersonCreatedEvent;
import de.atruvia.webapp.events.PersonDeletedEvent;
import de.atruvia.webapp.events.PersonUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {
    @EventListener
    public void handleEvent(PersonCreatedEvent event) {
        System.out.println("Created Event fired with " + event.person());
    }

    @EventListener
    public void handleEvent(PersonUpdatedEvent event) {
        System.out.println("Updated Event fired with " + event.person());
    }

    @EventListener
    public void handleEvent(PersonDeletedEvent event) {
        System.out.println("Updated Event fired with " + event.id());
    }
}
