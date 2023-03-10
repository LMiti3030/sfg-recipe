package mititelu.laura.sfg.services;

import lombok.extern.slf4j.Slf4j;
import mititelu.laura.sfg.domain.Recipe;
import mititelu.laura.sfg.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//gives us a logger
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in RecipeServiceImpl");
        Set<Recipe> recipes = new HashSet<>();
//        recipeRepository.findAll().forEach( recipe -> {
//            recipes.add(recipe);
//        });
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
}
