package mititelu.laura.sfg.bootstrap;

import lombok.extern.slf4j.Slf4j;
import mititelu.laura.sfg.domain.*;
import mititelu.laura.sfg.repositories.CategoryRepository;
import mititelu.laura.sfg.repositories.RecipeRepository;
import mititelu.laura.sfg.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

//    RecipeService recipeService;
    RecipeRepository recipeRepository;

    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //the lazy initialization of the sets in the entities, must be done in the same hibernate transaction
        log.debug("Loading chicken recipe");
        recipeRepository.save(addChickenRecipe());
        log.debug("Loading guacamole recipe");
        recipeRepository.save(addGuacamoleRecipe());
    }


    private UnitOfMeasure getUnitOfMeasure(String description, Map<String,UnitOfMeasure> uoms){
        UnitOfMeasure uom = uoms.get(description);
        if(uom == null){
            Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(description);
            if(!uomOptional.isPresent()){
                throw new RuntimeException(description + " UOM not found");
            }
            uom =uomOptional.get();
            uoms.put(description, uom);

        }
        return uom;
    }

    private Recipe addGuacamoleRecipe() {
        Recipe guacamole = new Recipe();

        Set<Category> guacamoleCategories = new HashSet<>();
        guacamoleCategories.add(categoryRepository.findByDescription("Mexican").get());

        guacamole.setCategories(guacamoleCategories);

        guacamole.setDescription("Perfect guacamole");

        guacamole.setCookTime(10);
        guacamole.setPrepTime(10);
        guacamole.setServings(4);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("""
                                
                Prepare the grill:
                                
                Prepare either a gas or charcoal grill for medium-high, direct heat.
                Make the marinade and coat the chicken:
                                
                In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.
                                
                Set aside to marinate while the grill heats and you prepare the rest of the toppings.
                Spicy Grilled Chicken Tacos
                Grill the chicken:
                                
                Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.
                Thin the sour cream with milk:
                                
                Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.
                Assemble the tacos:
                                
                Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.
                Warm the tortillas:
                                
                Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.
                                
                Wrap warmed tortillas in a tea towel to keep them warm until serving.
                """);

        guacamole.setDifficulty(Difficulty.EASY);

        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");

        guacamole.setNotes(notes);

        Map<String, UnitOfMeasure> uoms = new HashMap<>();

        guacamole.addIngredient(new Ingredient("ripe avocado", new BigDecimal(2), getUnitOfMeasure("Piece", uoms)));
        guacamole.addIngredient(new Ingredient("kosher salt, plus more to taste", new BigDecimal(0.25), getUnitOfMeasure("Teaspoon", uoms)));
        guacamole.addIngredient(new Ingredient("fresh lime or lemon juice", new BigDecimal(1), getUnitOfMeasure("Tablespoon", uoms)));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), getUnitOfMeasure("Tablespoon", uoms)));
        guacamole.addIngredient(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(1), getUnitOfMeasure("Piece", uoms)));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), getUnitOfMeasure("Tablespoon", uoms)));
        guacamole.addIngredient(new Ingredient("freshly ground black pepper", new BigDecimal(1), getUnitOfMeasure("Pinch", uoms)));
        guacamole.addIngredient(new Ingredient("ripe tomato, chopped (optional)", new BigDecimal(0.5), getUnitOfMeasure("Piece", uoms)));
        guacamole.addIngredient(new Ingredient("Red radish or jicama slices for garnish (optional)", new BigDecimal(1), getUnitOfMeasure("Piece", uoms)));

        return guacamole;
    }

    private Recipe addChickenRecipe() {
        Recipe chickenRecipe = new Recipe();

        Set<Category> chickenCategories = new HashSet<>();
        chickenCategories.add(categoryRepository.findByDescription("Fast food").get());

        chickenRecipe.setCategories(chickenCategories);

        chickenRecipe.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");

        chickenRecipe.setCookTime(15);
        chickenRecipe.setPrepTime(20);
        chickenRecipe.setServings(4);
        chickenRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenRecipe.setDirections("""
                                
                Prepare the grill:
                                
                Prepare either a gas or charcoal grill for medium-high, direct heat.
                Make the marinade and coat the chicken:
                                
                In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.
                                
                Set aside to marinate while the grill heats and you prepare the rest of the toppings.
                Spicy Grilled Chicken Tacos
                Grill the chicken:
                                
                Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.
                Thin the sour cream with milk:
                                
                Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.
                Assemble the tacos:
                                
                Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.
                Warm the tortillas:
                                
                Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.
                                
                Wrap warmed tortillas in a tea towel to keep them warm until serving.
                """);

        chickenRecipe.setDifficulty(Difficulty.MODERATE);

        Notes notes = new Notes();
        notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        chickenRecipe.setNotes(notes);

        Map<String, UnitOfMeasure> uoms = new HashMap<>();
        chickenRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), getUnitOfMeasure("Tablespoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), getUnitOfMeasure("Teaspoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), getUnitOfMeasure("Teaspoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), getUnitOfMeasure("Teaspoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal(0.5), getUnitOfMeasure("Teaspoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), getUnitOfMeasure("Piece", uoms)));
        chickenRecipe.addIngredient(new Ingredient("finely grated orange zest ", new BigDecimal(1), getUnitOfMeasure("Tablespoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), getUnitOfMeasure("Tablespoon", uoms)));
        chickenRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(1.25), getUnitOfMeasure("Pound", uoms)));


        return chickenRecipe;
    }


}
