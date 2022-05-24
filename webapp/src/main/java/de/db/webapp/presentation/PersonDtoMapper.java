package de.db.webapp.presentation;


import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.services.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {

    PersonDto convert(Person person);
    Person convert(PersonDto dto);
    Iterable<PersonDto> convert(Iterable<Person> personen);
}
