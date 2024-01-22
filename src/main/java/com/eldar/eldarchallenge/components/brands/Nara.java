package com.eldar.eldarchallenge.components.brands;

import com.eldar.eldarchallenge.components.inter.IBrand;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Nara implements IBrand {

    @Override
    public Double taxAmount() {
        LocalDate today = LocalDate.now();
        return (double) today.getDayOfMonth() * 0.5;
    }
}
