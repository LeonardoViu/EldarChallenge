package com.eldar.eldarchallenge.services.service.inter;

import com.eldar.eldarchallenge.entities.Card;

import java.util.Date;

public interface IService {

    public Boolean isOperationValid(Integer amount);
    public Boolean isCardValid(Date expiredDate);
    public Boolean isSameCard(Card card1, Card card2);
    public Double taxAmount(String brand);
    public String toString();
}
