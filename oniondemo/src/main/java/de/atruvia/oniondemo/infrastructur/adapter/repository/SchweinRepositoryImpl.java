package de.atruvia.oniondemo.infrastructur.adapter.repository;


import de.atruvia.oniondemo.application.port.outport.SchweinRepository;
import de.atruvia.oniondemo.domain.schwein.aggregate.Schwein;
import de.atruvia.oniondemo.infrastructur.adapter.repository.mapper.SchweinDocumentMapper;
import de.atruvia.oniondemo.infrastructur.adapter.repository.raw.SpringSchweinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class SchweinRepositoryImpl implements SchweinRepository {

    private final SpringSchweinRepository jpaRepository;
    private final SchweinDocumentMapper mapper;

    @Override
    public void save(final Schwein schwein) {
        jpaRepository.save(mapper.toDocument(schwein));
    }

    @Override
    public Optional<Schwein> findById(final UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Iterable<Schwein> findAll() {
        return StreamSupport.stream(jpaRepository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .toList();
    }
}
