package de.db.webapp.services.mapper;

import de.db.webapp.persistence.entities.PersonEntity;
import de.db.webapp.persistence.entities.SchweinEntity;
import de.db.webapp.services.model.Person;
import de.db.webapp.services.model.Schwein;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity entity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
}
