package com.abnamro.recipe.controller;

import com.abnamro.recipe.model.Recipe;
import com.abnamro.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    /**
     * To filter recipes based on request body
     * @param recipe
     * @return
     */
    @GetMapping(value = "/fetchRecipes", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Recipe>> fetchRecipes(@Valid @RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.fetchRecipes(recipe));
    }

    /**
     * Add recipe
     * @param recipe
     * @return
     */
    @PostMapping(value = "/addRecipe", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.addRecipe(recipe));

    }

    /**
     * To update Recipe
     * @param recipe
     * @param recipeId
     * @return
     */
    @PutMapping(value = "/updateRecipe/{recipeId}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable long recipeId){
        return ResponseEntity.ok(recipeService.updateRecipe(recipe, recipeId));
    }

    /**
     * Remove Recipe
     * @param recipeId
     */
    @DeleteMapping(value = "/removeRecipe/{recipeId}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> removeRecipe(@PathVariable long recipeId){
        recipeService.removeRecipe(recipeId);
        return ResponseEntity.ok().build();
    }

}
