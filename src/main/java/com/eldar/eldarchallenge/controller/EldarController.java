package com.eldar.eldarchallenge.controller;

import com.eldar.eldarchallenge.Exceptions.customExceptions.DateExpiredException;
import com.eldar.eldarchallenge.Exceptions.customExceptions.IncorrectAmountException;
import com.eldar.eldarchallenge.dto.ResponseDTO;
import com.eldar.eldarchallenge.entities.Card;
import com.eldar.eldarchallenge.service.EldarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EldarController {

    @Autowired
    public EldarService eldarService;

    @GetMapping(value = "/{cardNumber}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> getCard(@PathVariable Long cardNumber, @RequestParam Integer amount) throws DateExpiredException, IncorrectAmountException {
        if (eldarService.isOperationValid(amount)) {
            Card card = eldarService.findById(cardNumber);
            if (eldarService.isCardValid(card.getExpireDate())) {
                Double tax = eldarService.taxAmount();
                System.out.println("TAX: " + tax);
                ResponseDTO response = new ResponseDTO(amount * tax, card);
                System.out.println("AMOUNT TO RETURN:" + response.getAmountToReturn());
                return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
            }
            throw new DateExpiredException("The card is expired");
        }
        throw new IncorrectAmountException("The amount has to be lower than 1000");
    }
}

