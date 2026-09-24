package de.atruvia.oniondemo.infrastructur.adapter.repository.mapper;

import de.atruvia.oniondemo.domain.schwein.aggregate.Schwein;
import de.atruvia.oniondemo.infrastructur.adapter.repository.raw.entity.SchweinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDocumentMapper {

    SchweinEntity toDocument(Schwein schwein);

    default Schwein toDomain(SchweinEntity doc) {
        return Schwein.reconstitute(
                doc.getId(),
                doc.getVersion(),
                doc.getName(),
                doc.getGewicht(),
                doc.getDeletedAt()
        );
    }
}