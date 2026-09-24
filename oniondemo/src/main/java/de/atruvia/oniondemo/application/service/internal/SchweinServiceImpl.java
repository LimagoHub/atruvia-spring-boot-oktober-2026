package de.atruvia.oniondemo.application.service.internal;


import de.atruvia.oniondemo.application.port.outport.SchweinRepository;
import de.atruvia.oniondemo.application.service.SchweineService;
import de.atruvia.oniondemo.application.shared.DomainEventPublisher;
import de.atruvia.oniondemo.application.shared.NotFoundException;
import de.atruvia.oniondemo.domain.schwein.aggregate.Schwein;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class SchweinServiceImpl implements SchweineService {

    private final SchweinRepository schweinRepository;
    private final DomainEventPublisher eventPublisher;


    @Override
    public void speichern(final Schwein schwein) {
        Schwein neuesSchwein = Schwein.create(schwein.getId(),schwein.getName());
        saveAndPublish(neuesSchwein);
    }

    @Override
    public void taufen(final UUID id, final String name) {
        Schwein schwein = schweinRepository.findById(id).orElseThrow(()-> new NotFoundException("Kein Schwein", id));
        schwein.taufen(name);
        saveAndPublish(schwein);
    }

    @Override
    public void fuettern(final UUID id) {
        Schwein schwein = schweinRepository.findById(id).orElseThrow(()-> new NotFoundException("Kein Schwein", id));
        schwein.fuettern();
        saveAndPublish(schwein);
    }

    @Override
    public void loeschen(final UUID id) {
        Schwein schwein = schweinRepository.findById(id).orElseThrow(()-> new NotFoundException("Kein Schwein", id));
        schwein.markAsDeleted();
        saveAndPublish(schwein);
    }

    private void saveAndPublish(Schwein schwein) {
        schweinRepository.save(schwein);
        eventPublisher.publish(schwein.getDomainEvents());
        schwein.clearDomainEvents();
    }
}
