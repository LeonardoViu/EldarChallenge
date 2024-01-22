package com.eldar.eldarchallenge.services.command.inter;

import com.eldar.eldarchallenge.exceptions.customExceptions.DateExpiredException;
import com.eldar.eldarchallenge.exceptions.customExceptions.IncorrectAmountException;
import com.eldar.eldarchallenge.dto.ResponseDTO;

public interface ICommand {

    ResponseDTO execute(Long cardNumber, Integer amount) throws DateExpiredException, IncorrectAmountException;
}
