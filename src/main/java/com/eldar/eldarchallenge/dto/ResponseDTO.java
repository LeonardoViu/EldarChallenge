package com.eldar.eldarchallenge.dto;

import com.eldar.eldarchallenge.entities.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO extends Card {

    private Double amountToReturn;

    public ResponseDTO(double amount, Card card) {
        super.setBrand(card.getBrand());
        super.setFullName(card.getFullName());
        super.setCardNumber(card.getCardNumber());
        super.setExpireDate(card.getExpireDate());
        this.amountToReturn = amount;
    }
}
