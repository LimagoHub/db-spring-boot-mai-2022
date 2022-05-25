package de.db.webapp.repositories;

import de.db.webapp.persistence.entities.PersonEntity;
import de.db.webapp.persistence.entities.TinyPerson;
import de.db.webapp.services.model.Person;

import java.util.Optional;

public interface PersonenRepository {
    Iterable<Person> findByVorname(String vorname);

    Iterable<Person> fritz(String vorname);

    Iterable<Object[]> projektion();

    Iterable<TinyPerson> projektionBesser();



   Person save(Person entity);

    Optional<Person> findById(String s);

    boolean existsById(String s);

    Iterable<Person> findAll();

    long count();

    void deleteById(String s);

    void delete(Person entity);
}
