package de.db.webapp.ports;

import de.db.webapp.presentation.dtos.PersonDto;
import de.db.webapp.services.PersonenServiceException;

public interface PersonenHandler {
    void handleSpeichern(PersonDto dto) throws PersonenServiceException;

    void handleDelete(String id) throws PersonenServiceException;
}
