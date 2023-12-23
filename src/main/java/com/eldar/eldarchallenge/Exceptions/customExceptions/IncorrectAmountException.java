package com.eldar.eldarchallenge.Exceptions.customExceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class IncorrectAmountException extends Exception{

    @Builder
    public IncorrectAmountException(String message){
        super(message);
    }
}
