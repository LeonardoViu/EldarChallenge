package com.eldar.eldarchallenge.exceptions.customExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateExpiredException extends Exception{

    public DateExpiredException(String message){

        super(message);
    }
}
