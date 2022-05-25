package de.db.webapp.repositories;

import de.db.webapp.persistence.PersonenRepositoryRaw;
import de.db.webapp.persistence.entities.TinyPerson;
import de.db.webapp.repositories.mapper.PersonMapper;
import de.db.webapp.services.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PersonenRepositoryImpl implements PersonenRepository {

    private final PersonenRepositoryRaw rawRepo;
    private final PersonMapper mapper;

    @Override
    public Iterable<Person> findByVorname(String vorname) {
        return mapper.convert(rawRepo.findByVorname(vorname));
    }


    @Override
    public Iterable<Person> fritz(String vorname) {
        return mapper.convert(rawRepo.fritz(vorname));
    }


    @Override
    public Iterable<Object[]> projektion() {
        return rawRepo.projektion();
    }


    @Override
    public Iterable<TinyPerson> projektionBesser() {
        return rawRepo.projektionBesser();
    }



    @Override
    public Person save(Person entity) {
        return mapper.convert(rawRepo.save(mapper.convert(entity)));
    }


    @Override
    public Optional<Person> findById(String s) {
        return rawRepo.findById(s).map(mapper::convert);
    }

    @Override
    public boolean existsById(String s) {
        return rawRepo.existsById(s);
    }

    @Override
    public Iterable<Person> findAll() {
        return mapper.convert(rawRepo.findAll());
    }



    @Override
    public long count() {
        return rawRepo.count();
    }

    @Override
    public void deleteById(String s) {
        rawRepo.deleteById(s);
    }

    @Override
    public void delete(Person entity) {
        rawRepo.delete(mapper.convert(entity));
    }


}
