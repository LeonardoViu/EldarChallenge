package com.eldar.eldarchallenge.service.inter;

import com.eldar.eldarchallenge.dto.inter.IBrand;
import com.eldar.eldarchallenge.entities.Card;

import java.util.Date;
import java.util.Optional;

public interface IService {

    public String toString();
    public Boolean isOperationValid(Integer amount);
    public Boolean isCardValid(Date expiredDate);
    public Boolean isSameCard(Card card1, Card card2);
    public Double taxAmount();
}
