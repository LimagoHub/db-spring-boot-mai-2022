package de.db.webapp.persistence;

import de.db.webapp.persistence.entities.PersonEntity;
import de.db.webapp.persistence.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepositoryRaw extends CrudRepository<PersonEntity, String> , PersonenCustomRepository{

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p where p.vorname like :vorname")
    Iterable<PersonEntity> fritz(String vorname);

    @Query("select p.vorname, p.nachname from PersonEntity p")
    Iterable<Object[]> projektion();

    @Query("select new de.db.webapp.persistence.entities.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> projektionBesser();

    Iterable<PersonEntity> jane();
}
