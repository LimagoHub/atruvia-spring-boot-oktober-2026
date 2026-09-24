package de.atruvia.oniondemo.infrastructur.handler;


import de.atruvia.oniondemo.application.service.SchweineService;
import de.atruvia.oniondemo.domain.schwein.aggregate.Schwein;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ScweineServiceHandler {

    private final SchweineService service;

    @EventListener
    public void handle(Schwein schwein) {
        service.speichern(schwein);
    }
}
