package de.db.webapp.persistence;

import de.db.webapp.persistence.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> {

    Iterable<PersonEntity> findByVorname(String vorname);
}
