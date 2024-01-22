package com.eldar.eldarchallenge.services.command;

import com.eldar.eldarchallenge.exceptions.customExceptions.DateExpiredException;
import com.eldar.eldarchallenge.exceptions.customExceptions.IncorrectAmountException;
import com.eldar.eldarchallenge.dto.ResponseDTO;
import com.eldar.eldarchallenge.entities.Card;
import com.eldar.eldarchallenge.services.service.EldarService;
import com.eldar.eldarchallenge.services.command.inter.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidAndGetCardCommand implements ICommand {
    @Autowired
    private EldarService eldarService;

    @Override
    public ResponseDTO execute(Long cardNumber, Integer amount) throws DateExpiredException, IncorrectAmountException {
        if (eldarService.isOperationValid(amount)) {
            Card card = eldarService.findAndSetCard(cardNumber);
            if (eldarService.isCardValid(card.getExpireDate())) {
                Double tax = eldarService.taxAmount(card.getBrand());
                System.out.println("Obtuve el tax: " + tax);
                ResponseDTO response = new ResponseDTO(amount * tax, card);
                return response;
            }
            throw new DateExpiredException("The card is expired");
        }
        throw new IncorrectAmountException("The amount has to be lower than 1000");
    }
}
