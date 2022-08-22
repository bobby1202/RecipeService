package com.abnamro.recipe.respository;

import com.abnamro.recipe.model.Recipe;

import java.util.List;

public interface RecipeRepositoryCustom {

    public List<Recipe> getFilteredRecipes(Recipe recipe);
}
