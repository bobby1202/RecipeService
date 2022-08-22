package com.abnamro.recipe.service;

import com.abnamro.recipe.exception.RecipeServiceException;
import com.abnamro.recipe.model.Recipe;
import com.abnamro.recipe.respository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    RecipeServiceImpl(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    /**
     * fetch recipes
     * @param recipe
     * @return
     */
    public List<Recipe> fetchRecipes(final Recipe recipe){
        return recipeRepository.getFilteredRecipes(recipe);
    }
    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    /**
     * update recipe
     * @param recipe
     * @param recipeId
     * @return
     */
    @Override
    public Recipe updateRecipe(Recipe recipe, long recipeId) {
        var recipePresent = findRecipe(recipeId).isPresent();
        if(recipePresent) {
            log.debug("Updating Recipe - {} ", recipe);
            return recipeRepository.save(recipe);
        }else {
            log.error("Updating Recipe failed :: Recipe Id: {} not found", recipe.getRecipeId());
            throw new RecipeServiceException("Recipe update failed..");
        }
    }

    /**
     * remove recipe
     * @param recipeId
     */
    @Override
    public void removeRecipe(@NotNull final long recipeId) {
        var recipePresent = findRecipe(recipeId).isPresent();
        if(recipePresent) {
            recipeRepository.deleteById(recipeId);
            log.debug("Deleted Recipe with ID:{}", recipeId);
        }else {
            log.error("Removing recipe failed :: Recipe Id: {} not found",recipeId);
            throw new RecipeServiceException("Recipe deletion failed..");
        }
    }

    /**
     * To find recipe using recipeId
     * @param recipeId
     * @return
     */
    private Optional<Recipe> findRecipe(@NotNull long recipeId){
        return recipeRepository.findById(recipeId);
    }
}
