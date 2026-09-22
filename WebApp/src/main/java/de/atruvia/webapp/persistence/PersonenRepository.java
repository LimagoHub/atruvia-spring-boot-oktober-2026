package de.atruvia.webapp.persistence;

import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonenRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p")
    Iterable<PersonEntity> xyz();

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> abc();

    Iterable<TinyPerson> findAllProjectByVorname(String vorname);

}
