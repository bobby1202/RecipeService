package com.abnamro.recipe.controller;

import com.abnamro.recipe.model.DishType;
import com.abnamro.recipe.exception.RecipeServiceException;
import com.abnamro.recipe.model.Ingredients;
import com.abnamro.recipe.model.Recipe;
import com.abnamro.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    private Recipe recipe1;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RecipeController(recipeService)).build();

        recipe1 = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("oven")
                .build();
    }

    @Test
    @DisplayName("To test fetching recipe endpoint")
    void fetchRecipes() throws Exception{

        Recipe inputRecipe = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("oven")
                .build();
        given(recipeService.fetchRecipes(any(Recipe.class))).willReturn(new ArrayList<>(List.of(recipe1)));
        mockMvc.perform(get("/recipes/fetchRecipes").contentType(MediaType.APPLICATION_JSON)
                        .content(inputRecipe.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("To test fetching recipe endpoint::invalid input")
    void fetchRecipesErrorScenario1() throws Exception{

        given(recipeService.fetchRecipes(any(Recipe.class))).willReturn(new ArrayList<>(List.of(recipe1)));
        mockMvc.perform(get("/recipes/fetchRecipes").contentType(MediaType.APPLICATION_JSON)
                        .content("Invalid Data")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("To test adding recipe endpoint")
    void addRecipe() throws Exception{

        Recipe inputRecipe = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("oven")
                .build();
        given(recipeService.addRecipe(any(Recipe.class))).willReturn(recipe1);
        mockMvc.perform(post("/recipes/addRecipe").contentType(MediaType.APPLICATION_JSON)
                        .content(inputRecipe.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("To test updating recipe endpoint")
    void updateRecipe() throws Exception{

        Recipe inputRecipe = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("oven")
                .build();
        given(recipeService.addRecipe(any(Recipe.class))).willReturn(recipe1);
        mockMvc.perform(put("/recipes/updateRecipe/{recipeId}",1).contentType(MediaType.APPLICATION_JSON)
                        .content(inputRecipe.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("To test remove recipe endpoint")
    void removeRecipes() throws Exception{
        doNothing().when(recipeService).removeRecipe(anyLong());
        mockMvc.perform(delete("/recipes/removeRecipe/{recipeId}",1))
                .andExpect(status().isOk());
    }
}