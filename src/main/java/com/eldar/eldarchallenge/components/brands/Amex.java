package com.eldar.eldarchallenge.components.brands;

import com.eldar.eldarchallenge.components.inter.IBrand;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Amex implements IBrand {

    @Override
    public Double taxAmount() {
        LocalDate today = LocalDate.now();
        return (double) today.getMonthValue() * 0.1;
    }
}
