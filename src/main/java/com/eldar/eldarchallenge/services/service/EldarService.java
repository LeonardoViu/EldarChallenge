package com.eldar.eldarchallenge.services.service;

import com.eldar.eldarchallenge.components.inter.IBrand;
import com.eldar.eldarchallenge.entities.Card;
import com.eldar.eldarchallenge.repositories.CardRep;
import com.eldar.eldarchallenge.services.service.inter.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class EldarService implements IService {

    private Card serviceCard;
    @Autowired
    public CardRep cardRep;

    /* ---------- 3 objects/brands ---------- */
    public HashMap<String, IBrand> brands = new HashMap<>();
    /* -------------------------------------- */

    @Autowired
    public EldarService(List<IBrand> brands){
        for(IBrand brand : brands){
            this.brands.put(brand.getClass().getSimpleName(), brand);
        }
    }

    /* -------------- 5 methods ------------- */
    @Override
    public Boolean isOperationValid(Integer amount) {
        return (amount <= 1000);
    }

    @Override
    public Boolean isCardValid(Date expiredDate) {
        Date currentDate = new Date();
        return currentDate.before(expiredDate);
    }

    @Override
    public Boolean isSameCard(Card card1, Card card2) {
        long number1 = card1.getCardNumber();
        long number2 = card2.getCardNumber();
        return (number1 == number2);
    }

    @Override
    public Double taxAmount(String brand) {
        return brands.get(brand).taxAmount();
    }

    @Override
    public String toString() {
        return "TuClase{" +
                "cardNumber=" + serviceCard.getCardNumber() +
                ", fullName='" + serviceCard.getFullName() + '\'' +
                ", expireDate=" + serviceCard.getExpireDate() +
                ", brand='" + serviceCard.getBrand() + '\'' +
                '}';
    }
    /* -------------------------------------- */

    public Card findAndSetCard(Long cardNumber) {
        Card response = cardRep.findById(cardNumber).get();
        serviceCard = response;
        return response;
    }
}