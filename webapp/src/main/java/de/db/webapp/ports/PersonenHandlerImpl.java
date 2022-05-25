package de.db.webapp.ports;


import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.presentation.mapper.PersonDtoMapper;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
public class PersonenHandlerImpl implements PersonenHandler {
    private final PersonenService service;
    private final PersonDtoMapper mapper;

    @Override
    public void handleSpeichern(PersonDto dto) throws PersonenServiceException{
        try {
            service.speichern(mapper.convert(dto));

            // Fire Success Event

        } catch (PersonenServiceException e) {
            // Fire Failure Event
            throw e;
        }
    }

    @Override
    public void handleDelete(String id) throws PersonenServiceException{
        try {
            service.loeschen(id);

            // Fire Success Event

        } catch (PersonenServiceException e) {
            // Fire Failure Event
            throw e;
        }
    }
}
