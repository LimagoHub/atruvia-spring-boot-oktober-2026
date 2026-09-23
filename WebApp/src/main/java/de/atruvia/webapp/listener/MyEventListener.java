package de.atruvia.webapp.listener;


import de.atruvia.webapp.events.PersonCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {
    @EventListener
    public void handlePersonCreatedEvent(PersonCreatedEvent event) {
        System.out.println("Created Event fired with " + event.person());
    }
}
