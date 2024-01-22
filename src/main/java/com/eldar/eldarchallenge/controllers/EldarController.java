package com.eldar.eldarchallenge.controllers;

import com.eldar.eldarchallenge.exceptions.customExceptions.DateExpiredException;
import com.eldar.eldarchallenge.exceptions.customExceptions.IncorrectAmountException;
import com.eldar.eldarchallenge.dto.ResponseDTO;
import com.eldar.eldarchallenge.services.command.ValidAndGetCardCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EldarController {

    @Autowired
    private ValidAndGetCardCommand getCommand;

    @GetMapping(value = "/{cardNumber}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> getCard(@PathVariable Long cardNumber, @RequestParam Integer amount) throws DateExpiredException, IncorrectAmountException {
        ResponseDTO response = getCommand.execute(cardNumber, amount);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
}

