package de.atruvia.oniondemo.application.service;

import de.atruvia.oniondemo.domain.schwein.aggregate.Schwein;

import java.util.UUID;

public interface SchweineService {

    void speichern(Schwein schwein);
    void taufen(UUID id, String name);
    void fuettern(UUID id);
    void loeschen(UUID id);
}
