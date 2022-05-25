package de.db.webapp.services;

import de.db.webapp.persistence.PersonenRepository;
import de.db.webapp.persistence.SchweineRepository;
import de.db.webapp.persistence.entities.SchweinEntity;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.mapper.SchweinMapper;
import de.db.webapp.services.model.Person;
import de.db.webapp.services.model.Schwein;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SchweineServiceException.class, isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
public class SchweineServiceImpl implements SchweineService {

    private final SchweineRepository repo;
    private final SchweinMapper mapper;


    @Override
    public boolean speichern(Schwein schwein) throws SchweineServiceException{


        try {
            boolean retval = false;
            if(schwein == null)
                throw  new SchweineServiceException("Schwein darf nicht null sein!");

            if(schwein.getId() == null)
                throw  new SchweineServiceException("Id darf nicht null sein!");

            if(schwein.getName() == null || schwein.getName().length() < 2)
                throw  new SchweineServiceException("Name muss mindestens 2 Zeichen enthalten!");

            retval = repo.existsById(schwein.getId());

            repo.save(mapper.convert(schwein));
            return retval;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }


    }

    @Override
    public boolean loeschen(Schwein schwein) throws SchweineServiceException {
        return loeschen(schwein.getId());
    }

    @Override
    public boolean loeschen(String id) throws SchweineServiceException {
        try {
            boolean result = repo.existsById(id);
            repo.deleteById(id);
            return result;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public Optional<Schwein> findeNachId(String id) throws SchweineServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Schwein> findeAll() throws SchweineServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public boolean fuettern(String id) throws SchweineServiceException {
        try {
            Optional<Schwein> schweinOptional = findeNachId(id);
            if (schweinOptional.isPresent()) {
                Schwein schwein = schweinOptional.get();
                schwein.fuettern();
                speichern(schwein);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }


}
