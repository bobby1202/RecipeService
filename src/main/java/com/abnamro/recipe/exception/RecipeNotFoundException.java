package com.abnamro.recipe.exception;

/**
 * Recipe Not Found Exception Class
 */
public class RecipeNotFoundException extends RuntimeException{


    public RecipeNotFoundException(final String message) {
        super(message);
    }

    public RecipeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
