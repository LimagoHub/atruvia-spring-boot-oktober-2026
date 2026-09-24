package de.atruvia.oniondemo.application.port.outport;


import de.atruvia.oniondemo.domain.schwein.aggregate.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweinRepository {
    void save(Schwein schwein);
    Optional<Schwein> findById(UUID id);
    Iterable<Schwein> findAll();
}
