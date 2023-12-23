package com.eldar.eldarchallenge;

import com.eldar.eldarchallenge.repositories.CardRep;
import com.eldar.eldarchallenge.entities.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class EldarChallengeApplication {

    @Autowired
    public CardRep cardRep;

    public static void main(String[] args) {
        SpringApplication.run(EldarChallengeApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return (args) -> { setDB();
        };
    }

    public void setDB(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2024, Calendar.JANUARY, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2024, Calendar.MARCH, 1);

        cardRep.save(new Card(1234567890L, "John Doe", calendar1.getTime(), "Visa"));
        cardRep.save(new Card(9876543210L, "Jane Smith", calendar2.getTime(), "Amex"));
        cardRep.save(new Card(1111222333L, "Alice Johnson", new Date(), "Nara"));
    }

}
