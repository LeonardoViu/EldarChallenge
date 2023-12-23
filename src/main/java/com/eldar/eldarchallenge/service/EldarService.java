package com.eldar.eldarchallenge.service;

import com.eldar.eldarchallenge.dto.brands.Amex;
import com.eldar.eldarchallenge.dto.brands.Nara;
import com.eldar.eldarchallenge.dto.brands.Visa;
import com.eldar.eldarchallenge.dto.inter.IBrand;
import com.eldar.eldarchallenge.entities.Card;
import com.eldar.eldarchallenge.repositories.CardRep;
import com.eldar.eldarchallenge.service.inter.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EldarService implements IService {

    Card serviceCard = null;
    @Autowired
    public CardRep cardRep;

    /* ---------- 3 posible object ---------- */
    public IBrand brand = null;
    /* -------------------------------------- */

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
        return card1.getCardNumber() == card2.getCardNumber();
    }

    @Override
    public Double taxAmount() {
        return brand.taxAmount();
    }

    @Override
    public String toString() {
        return "TuClase{" +
                "cardNumber=" + serviceCard.getCardNumber() +
                ", fullName='" + serviceCard.getFullName() + '\'' +
                ", expireDate=" + serviceCard.getExpireDate() +
                ", brand='" + brand + '\'' +
                '}';
    }
    /* -------------------------------------- */

    public Card findById(Long cardNumber) {
        Card response = cardRep.findById(cardNumber).get();
        serviceCard = response;
        initializeBrand(response.getBrand());
        return response;
    }

    public void initializeBrand(String newBrand) {
        switch(newBrand){
            case "Amex": this.brand = new Amex(); break;
            case "Nara": this.brand = new Nara(); break;
            case "Visa": this.brand = new Visa(); break;
            default: break;
        }
    }
}