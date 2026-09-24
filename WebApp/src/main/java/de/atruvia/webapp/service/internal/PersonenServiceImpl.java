package de.atruvia.webapp.service.internal;

import de.atruvia.webapp.events.PersonCreatedEvent;
import de.atruvia.webapp.events.PersonDeletedEvent;
import de.atruvia.webapp.events.PersonUpdatedEvent;
import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.service.BlacklistService;
import de.atruvia.webapp.service.PersonenService;
import de.atruvia.webapp.service.exception.AlreadyExistsException;
import de.atruvia.webapp.service.exception.BlacklistException;
import de.atruvia.webapp.service.exception.NotFoundException;
import de.atruvia.webapp.service.exception.PersonenServiceException;
import de.atruvia.webapp.service.mapper.PersonMapper;
import de.atruvia.webapp.service.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService
{

    private final PersonenRepository repo;
    private final PersonMapper mapper;
    //private final BlacklistService blacklistService;

    @Qualifier("antipathen")
    private final List<String> antipathen;

    private final ApplicationEventPublisher applicationEventPublisher;



       /*
        person ist null -> PSE
        vorname ist -> PSE
        vorname zu kurz -> PSE
        das selbe für Nachname
        vorname Attila -> PSE
        technische Exceptions aller art wandeln in PSE
        Happy Day wird person an repo uebergeben

     */

    @Override
    public void speichern(final Person person) throws PersonenServiceException {
        try {
            if (person == null) throw new PersonenServiceException("Person darf nicht null sein");
            if (repo.existsById(person.getId())) throw new AlreadyExistsException("Datensatz existiert bereits");
            if (person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
            if (person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");

            //if(blacklistService.isBlacklisted(person))  throw new BlacklistException("Antipath");
            if(antipathen.contains(person.getVorname())) throw new BlacklistException("Antipath");
            repo.save(mapper.convert(person));
            applicationEventPublisher.publishEvent(new PersonCreatedEvent(person));
        } catch (AlreadyExistsException  |BlacklistException e) {
            throw e;
        }catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern",e);

        }
    }

    @Override
    public void aendern(final Person person) throws PersonenServiceException {
        try {
            if (person == null) throw new PersonenServiceException("Person darf nicht null sein");
            if (! repo.existsById(person.getId())) throw new NotFoundException("Gippps nich!");
            if (person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
            if (person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");

            if("Attila".equals(person.getVorname()))  throw new PersonenServiceException("Antipath");
            repo.save(mapper.convert(person));
            applicationEventPublisher.publishEvent(new PersonUpdatedEvent(person));
        } catch (NotFoundException e) {
            throw e;
        }catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Aendern",e);
        }
    }

    @Override
    public void loeschen(final UUID uuid) throws PersonenServiceException {
        try {
            if (! repo.existsById(uuid)) throw new NotFoundException("Datensatz konnte nicht gefunden werden");
            repo.deleteById(uuid);
            applicationEventPublisher.publishEvent(new PersonDeletedEvent(uuid));
        } catch (NotFoundException e) {
            throw e;
        }catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Loeschen",e);
        }
    }


    @Transactional( isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findeNachId(final UUID uuid) throws PersonenServiceException {
        try {

            return repo.findById(uuid).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Loeschen",e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());

        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Suchen",  e);
        }
    }
}
