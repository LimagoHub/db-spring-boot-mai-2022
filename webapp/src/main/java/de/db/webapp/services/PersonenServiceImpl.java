package de.db.webapp.services;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.model.Person;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;



@AllArgsConstructor
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;

    private final List<String> antipathen;

    @Override
    public boolean speichern(Person person) throws PersonenServiceException{


        try {
            return speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }


    }

    private boolean speichernImpl(Person person) throws PersonenServiceException {
        boolean retval = false;
        checkPerson(person);

        retval = repo.existsById(person.getId());

        repo.save(person);
        return retval;
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(antipathen.contains(person.getVorname()))
            throw  new PersonenServiceException("Antipath!");
    }

    private void validatePerson(Person person) throws PersonenServiceException {
        if(person == null)
            throw  new PersonenServiceException("Person darf nicht null sein!");

        if(person.getId() == null)
            throw  new PersonenServiceException("Id darf nicht null sein!");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw  new PersonenServiceException("Vorname muss mindestens 2 Zeichen enthalten!");

        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw  new PersonenServiceException("Nachname muss mindestens 2 Zeichen enthalten!");
    }

    @Override
    public boolean loeschen(Person person) throws PersonenServiceException {
        return loeschen(person.getId());
    }

    @Override
    public boolean loeschen(String id) throws PersonenServiceException {
        try {
            boolean result = repo.existsById(id);
            repo.deleteById(id);
            return result;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Optional<Person> findeNachId(String id) throws PersonenServiceException {
        try {
            return repo.findById(id);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Person> findeAll() throws PersonenServiceException {
        try {
            return repo.findAll();
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }


}
