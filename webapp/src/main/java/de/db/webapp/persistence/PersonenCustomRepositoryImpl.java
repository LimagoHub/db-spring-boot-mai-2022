package de.db.webapp.persistence;

import de.db.webapp.persistence.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


public class PersonenCustomRepositoryImpl implements PersonenCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void persist(PersonEntity person) {
        em.persist(person);
    }
}
