package de.db.webapp.persistence;

import de.db.webapp.persistence.entities.PersonEntity;

public interface PersonenCustomRepository {
    void persist(PersonEntity person);
}
