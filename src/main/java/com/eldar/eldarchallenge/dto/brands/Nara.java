package com.eldar.eldarchallenge.dto.brands;

import com.eldar.eldarchallenge.dto.inter.IBrand;

import java.time.LocalDate;

public class Nara implements IBrand {

    @Override
    public Double taxAmount() {
        LocalDate today = LocalDate.now();
        return (double) today.getDayOfMonth() * 0.5;
    }
}
