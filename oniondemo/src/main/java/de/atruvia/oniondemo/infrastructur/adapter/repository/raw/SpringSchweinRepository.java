package de.atruvia.oniondemo.infrastructur.adapter.repository.raw;


import de.atruvia.oniondemo.infrastructur.adapter.repository.raw.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpringSchweinRepository extends CrudRepository<SchweinEntity, UUID> {


}
