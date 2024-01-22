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

    //El método initData() está anotado con @Bean y devuelve una instancia de CommandLineRunner.
    //Este bean se registra automáticamente en el contexto de Spring y se ejecuta al inicio de la app.
    //Cuando la aplicación arranca, Spring ejecutará automáticamente el método run de la interfaz
    //CLR, que a su vez ejecuta el código dentro del lambda (lambda seria la implementación
    // del metodo run de la interfaz funcional CLR)
    @Bean
    public CommandLineRunner initData() {
        return (args) -> { setDB(); };
    }

    public void setDB(){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2025, Calendar.JANUARY, 1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2025, Calendar.MARCH, 1);

        cardRep.save(new Card(1234567890L, "John Doe", calendar1.getTime(), "Visa"));
        cardRep.save(new Card(9876543210L, "Jane Smith", calendar2.getTime(), "Amex"));
        cardRep.save(new Card(1111222333L, "Alice Johnson", new Date(), "Nara"));
    }

}