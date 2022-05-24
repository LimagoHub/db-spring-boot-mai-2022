package de.db.webapp.services;

import de.db.webapp.services.model.Person;

import java.util.Optional;

public interface PersonenService {
    boolean speichern(Person person) throws PersonenServiceException;
    boolean loeschen(Person person) throws PersonenServiceException;
    boolean loeschen(String id) throws PersonenServiceException;
    Optional<Person> findeNachId(String id) throws PersonenServiceException;
    Iterable<Person> findeAll() throws PersonenServiceException;

}
