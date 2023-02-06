package mititelu.laura.sfg.controllers;

import mititelu.laura.sfg.domain.Recipe;
import mititelu.laura.sfg.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @InjectMocks
    IndexController indexController;

    //no longer required if using @InjectMocks
//    @BeforeEach
//    void setUp() {
//        indexController = new IndexController(recipeService);
//    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/")) //pass url for get mapping
                .andExpect(MockMvcResultMatchers.status().isOk()) //aserts status ok from the controller
                .andExpect(MockMvcResultMatchers.view().name("index")); //checks the name of the view returned

    }


    @Test
    void getIndexPage() {

        //given
        String expected = "index";

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setDescription("Mock");
        recipes.add(recipe);
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        //creates an argument captor for the set
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String actual = indexController.getIndexPage(model);

        //then
        assertEquals(expected, actual);

        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
//      Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), Mockito.anySet());

        //argumentCaptor.capture() - capture what is passed into the 2nd param by the recipeService.getRecipes()
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());

        //retrieving the catched value
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}