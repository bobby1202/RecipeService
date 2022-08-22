package com.abnamro.recipe.service;


import com.abnamro.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe addRecipe(final Recipe recipe);
    Recipe updateRecipe(final Recipe recipe, long recipeId);
    void removeRecipe(final long recipeId);
    List<Recipe> fetchRecipes(final Recipe recipe);

}
