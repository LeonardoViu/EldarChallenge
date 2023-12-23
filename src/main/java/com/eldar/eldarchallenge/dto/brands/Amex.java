package com.eldar.eldarchallenge.dto.brands;

import com.eldar.eldarchallenge.dto.inter.IBrand;

import java.time.LocalDate;
import java.util.Date;

public class Amex implements IBrand {

    @Override
    public Double taxAmount() {
        LocalDate today = LocalDate.now();
        return (double) (today.getYear() % 100)/(today.getMonthValue());
    }
}
