package de.db.webapp.persistence;

import de.db.webapp.persistence.entities.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

public interface SchweineRepository extends CrudRepository<SchweinEntity, String> {

}
