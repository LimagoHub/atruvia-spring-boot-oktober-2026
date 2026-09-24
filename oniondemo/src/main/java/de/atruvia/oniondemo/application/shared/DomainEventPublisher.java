package de.atruvia.oniondemo.application.shared;


import java.util.List;
// Port
public interface DomainEventPublisher {
    void publish(List<Object> events);
}
