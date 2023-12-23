package com.eldar.eldarchallenge.Exceptions;

import com.eldar.eldarchallenge.Exceptions.customExceptions.DateExpiredException;
import com.eldar.eldarchallenge.Exceptions.customExceptions.IncorrectAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(NoSuchElementException e) {
        return new ResponseEntity<String>("A NoSuchElement error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(NullPointerException e) {
        return new ResponseEntity<String>("A NullPointer error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* Custom Exceptions */
    @ExceptionHandler(DateExpiredException.class)
    public ResponseEntity<String> handleException(DateExpiredException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(IncorrectAmountException.class)
    public ResponseEntity<String> handleException(IncorrectAmountException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
