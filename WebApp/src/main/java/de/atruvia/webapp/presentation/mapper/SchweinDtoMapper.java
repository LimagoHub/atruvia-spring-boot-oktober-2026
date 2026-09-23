package de.atruvia.webapp.presentation.mapper;


import de.atruvia.webapp.presentation.dto.SchweinDto;
import de.atruvia.webapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {

    SchweinDto convert(Schwein schwein);
    Schwein convert(SchweinDto schweinDto);
    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);
}
