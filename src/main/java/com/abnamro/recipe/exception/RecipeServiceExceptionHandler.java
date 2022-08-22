package com.abnamro.recipe.exception;

import com.abnamro.recipe.controller.RecipeController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(assignableTypes = RecipeController.class)
public class RecipeServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RecipeServiceException.class})
    public ResponseEntity<Object> handleRecipeServiceException(RecipeServiceException exception, WebRequest request) {
        log.error("Exception occurred with message : {} ", exception.getMessage());
       return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RecipeNotFoundException.class})
    public ResponseEntity<Object> handleRecipeNotFoundException(RecipeNotFoundException exception, WebRequest request) {
        log.error("Exception occurred with message : {} ", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
