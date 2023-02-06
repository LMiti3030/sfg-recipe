package mititelu.laura.sfg.repositories;

import mititelu.laura.sfg.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    public Optional<UnitOfMeasure> findByDescription(String description);
}
