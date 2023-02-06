package mititelu.laura.sfg.repositories;

import mititelu.laura.sfg.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
