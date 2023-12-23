package com.eldar.eldarchallenge.dto.brands;

import com.eldar.eldarchallenge.dto.inter.IBrand;

import java.time.LocalDate;

public class Visa implements IBrand {

    @Override
    public Double taxAmount() {
        LocalDate today = LocalDate.now();
        return (double) (today.getYear() % 100)/(today.getMonthValue());
    }

}
