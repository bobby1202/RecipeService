package com.abnamro.recipe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RecipeServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RecipeServiceException.class})
    public ResponseEntity<Object> handleBusinessLoansServiceException(final RecipeServiceException exception) {
        log.error("Exception occurred with message : {} ", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
