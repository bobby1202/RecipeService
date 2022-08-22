package com.abnamro.recipe.service;

import com.abnamro.recipe.exception.RecipeNotFoundException;
import com.abnamro.recipe.exception.RecipeServiceException;
import com.abnamro.recipe.model.DishType;
import com.abnamro.recipe.model.Ingredients;
import com.abnamro.recipe.model.Recipe;
import com.abnamro.recipe.respository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    public RecipeServiceImpl recipeServiceImpl;

    private Recipe recipe1;

    private Recipe recipe2;

    private final Long recipeId = Long.valueOf(1);

    @BeforeEach
    void setUp(){
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);

        recipe1 = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("oven")
                .build();

        recipe2 = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("cook in oven")
                .build();

    }

    @Test
    @DisplayName("To test fetching recipes using given input")
    void fetchRecipes() {
        Recipe inputRecipe = Recipe.builder()
                .dishType(DishType.vegetarian)
                .noOfServings(4)
                .ingredients(new Ingredients("potatoes","salmon"))
                .recipeInstructions("oven")
                .build();

        List<Recipe> recipeList = new ArrayList<>();
        recipe1.setRecipeId(recipeId);
        recipe2.setRecipeId(Long.valueOf(2));
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        given(recipeRepository.getFilteredRecipes(inputRecipe)).willReturn(recipeList);
        assertEquals(recipeServiceImpl.fetchRecipes(inputRecipe),recipeList);
    }

    @Test
    @DisplayName("To test adding recipe")
    void addRecipe() {
        Recipe addRecipe = recipe1;
        addRecipe.setRecipeId(recipeId);
        given(recipeRepository.save(recipe1)).willReturn(addRecipe);
        Assertions.assertEquals(recipeServiceImpl.addRecipe(recipe1),addRecipe);
    }

    @Test
    @DisplayName("To test updating recipe")
    void updateRecipe() {
        recipe1.setRecipeId(recipeId);
        given(recipeRepository.findById(recipeId)).willReturn(Optional.of(recipe1));
        given(recipeRepository.save(recipe1)).willReturn(recipe1);
        Assertions.assertEquals(recipeServiceImpl.updateRecipe(recipe1,recipeId),recipe1);
    }

    @Test
    @DisplayName("To test updating recipe :: throws Error")
    void updateRecipeErrorScenario() {
        recipe1.setRecipeId(recipeId);
        given(recipeRepository.findById(recipeId)).willReturn(Optional.empty());
        Exception exception = assertThrows(RecipeNotFoundException.class, ()->{recipeServiceImpl.updateRecipe(recipe1,recipeId);});
        assertEquals(exception.getMessage(),"Recipe Id:"+recipeId+" not found for update");
    }

    @Test
    @DisplayName("To test removing recipe")
    void removeRecipe() {
        given(recipeRepository.findById(recipeId)).willReturn(Optional.of(recipe1));
        doNothing().when(recipeRepository).deleteById(recipeId);
        recipeServiceImpl.removeRecipe(recipeId);

    }

    @Test
    @DisplayName("To test updating recipe :: throws Error")
    void removeRecipeErrorScenario() {
        given(recipeRepository.findById(recipeId)).willReturn(Optional.empty());
        Exception exception = assertThrows(RecipeNotFoundException.class, ()->{recipeServiceImpl.removeRecipe(recipeId);});
        assertEquals(exception.getMessage(),"Recipe Id:"+recipeId+" not found for removing");
    }
}