package com.abnamro.recipe.respository;

import com.abnamro.recipe.model.Recipe;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

   @Override
    public List<Recipe> getFilteredRecipes(Recipe recipe) {

        String nativeQuery = "select recipe.recipe_id as recipeId, recipe.dish_type as dishType, recipe.no_of_servings as noOfServings, recipe.ingredients as ingredients, recipe.recipe_instructions as recipeInstructions from Recipe recipe where 1=1 ";
        if(recipe.getDishType()!=null)
            nativeQuery+=" and recipe.dish_type = '"+recipe.getDishType()+"'";
        if(recipe.getNoOfServings()!=null)
            nativeQuery+=" and recipe.no_of_servings = "+recipe.getNoOfServings();
        if(recipe.getIngredients()!=null){
            var includeIngredient = recipe.getIngredients().getInclude();
            var excludeIngredient = recipe.getIngredients().getExclude();
            if(includeIngredient!=null){
                    nativeQuery+=" and recipe.ingredients like '%\"include\":\""+includeIngredient+"\"%'";
            }
            if(excludeIngredient!=null){
                    nativeQuery+=" and recipe.ingredients like '%\"exclude\":\""+excludeIngredient+"\"%'";
            }
        }
        if(recipe.getRecipeInstructions()!=null)
            nativeQuery+=" and recipe.recipe_instructions like '%"+recipe.getRecipeInstructions().toLowerCase()+"%'";

        Query query = entityManager.createNativeQuery(nativeQuery, Recipe.class);

        return query.getResultList();
    }
}
