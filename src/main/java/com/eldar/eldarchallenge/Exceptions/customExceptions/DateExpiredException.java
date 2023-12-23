package com.eldar.eldarchallenge.Exceptions.customExceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DateExpiredException extends Exception{

    @Builder
    public DateExpiredException(String message){
        super(message);
    }
}
