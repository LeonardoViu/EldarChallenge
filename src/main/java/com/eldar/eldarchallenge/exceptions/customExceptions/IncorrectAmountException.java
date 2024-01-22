package com.eldar.eldarchallenge.exceptions.customExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectAmountException extends Exception{

    public IncorrectAmountException(String message){
        super(message);
    }
}
