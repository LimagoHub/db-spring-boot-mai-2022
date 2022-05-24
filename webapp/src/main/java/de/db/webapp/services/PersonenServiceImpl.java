package de.db.webapp.services;

import de.db.webapp.persistence.PersonenRepository;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;


    @Override
    public boolean speichern(Person person) throws PersonenServiceException{


        try {
            boolean retval = false;
            if(person == null)
                throw  new PersonenServiceException("Person darf nicht null sein!");

            if(person.getId() == null)
                throw  new PersonenServiceException("Id darf nicht null sein!");

            if(person.getVorname() == null || person.getVorname().length() < 2)
                throw  new PersonenServiceException("Vorname muss mindestens 2 Zeichen enthalten!");

            if(person.getNachname() == null || person.getNachname().length() < 2)
                throw  new PersonenServiceException("Nachname muss mindestens 2 Zeichen enthalten!");

            if(person.getVorname().equals("Attila"))
                throw  new PersonenServiceException("Antipath!");

            retval = repo.existsById(person.getId());

            repo.save(mapper.convert(person));
            return retval;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }


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
            return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Person> findeAll() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }


}
