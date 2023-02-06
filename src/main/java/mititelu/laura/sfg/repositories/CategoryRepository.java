package mititelu.laura.sfg.repositories;

import mititelu.laura.sfg.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    public Optional<Category> findByDescription(String description);
}
