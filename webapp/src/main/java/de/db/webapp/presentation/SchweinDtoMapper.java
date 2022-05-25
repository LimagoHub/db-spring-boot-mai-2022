package de.db.webapp.presentation;


import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.presentation.dtos.SchweinDto;
import de.db.webapp.services.model.Person;
import de.db.webapp.services.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {

    SchweinDto convert(Schwein schwein);
    Schwein convert(SchweinDto dto);
    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);
}
