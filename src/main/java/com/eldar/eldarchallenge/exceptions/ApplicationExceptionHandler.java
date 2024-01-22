package com.eldar.eldarchallenge.exceptions;

import com.eldar.eldarchallenge.exceptions.customExceptions.DateExpiredException;
import com.eldar.eldarchallenge.exceptions.customExceptions.IncorrectAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    /* Custom Exceptions */
    @ExceptionHandler(DateExpiredException.class)
    public ResponseEntity<String> handleException(DateExpiredException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(IncorrectAmountException.class)
    public ResponseEntity<String> handleException(IncorrectAmountException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /* Normal Exceptions */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> handleException(NoSuchElementException e) {
        return new ResponseEntity<String>("A NoSuchElement error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(NullPointerException e) {
        return new ResponseEntity<String>("A NullPointer error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "An Exception error occurred: " + e.getMessage();
    }
}
