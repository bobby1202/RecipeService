package com.abnamro.recipe.exception;


public class RecipeServiceException extends RuntimeException{

    public RecipeServiceException(final String message) {
        super(message);
    }

    public RecipeServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
