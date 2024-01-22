package com.eldar.eldarchallenge;

import com.eldar.eldarchallenge.entities.Card;
import com.eldar.eldarchallenge.repositories.CardRep;
import com.eldar.eldarchallenge.services.service.EldarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceTests {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public CardRep cardRep;
    @Autowired
    public EldarService eldarService;

    LocalDate date1;
    LocalDate date2;

    Card cardVisa;
    Card cardAmex;
    Card cardNara;
    Card cardNaraCopy;
    @BeforeEach
    public void setup() {
        date1 = LocalDate.of(2025, Month.JANUARY, 1);
        date2 = LocalDate.of(2025, Month.MARCH, 1);

        cardVisa = new Card(1234567890L, "John Doe", java.sql.Date.valueOf(date1), "Visa");
        cardAmex = new Card(9876543210L, "Jane Smith", java.sql.Date.valueOf(date2), "Amex");
        cardNara = new Card(1111222333L, "Alice Johnson", new Date(), "Nara");
        cardNaraCopy = new Card(1111222333L, "Alice Johnson", new Date(), "Nara");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void method1() throws Exception {
        Boolean result11 = eldarService.isOperationValid(1800);
        Boolean result12 = eldarService.isOperationValid(800);
        assertFalse(result11);
        assertTrue(result12);
    }

    @Test
    public void method2() throws Exception{
        Boolean result21 = eldarService.isCardValid(cardAmex.getExpireDate());
        Boolean result22 = eldarService.isCardValid(cardNara.getExpireDate());
        assertTrue(result21);
        assertFalse(result22);
    }

    @Test
    public void method3() throws Exception{
        Boolean result31 = eldarService.isSameCard(cardVisa, cardAmex);
        Boolean result32 = eldarService.isSameCard(cardNara, cardNaraCopy);
        assertFalse(result31);
        assertTrue(result32);
    }

    @Test
    public void method4() throws Exception{
        Double result41 = eldarService.taxAmount(cardVisa.getBrand());
        Double result42 = eldarService.taxAmount(cardAmex.getBrand());
        Double result43 = eldarService.taxAmount(cardNara.getBrand());
        LocalDate today = LocalDate.now();
        Double expectedVisa = (double) (today.getYear() % 100)/(today.getMonthValue());
        Double expectedAmex = (double) today.getMonthValue() * 0.1;
        Double expectedNara = (double) today.getDayOfMonth() * 0.5;
        assertEquals(expectedVisa, result41);
        assertEquals(expectedAmex, result42);
        assertEquals(expectedNara, result43);
    }

    @Test
    public void method5() throws Exception{
        when(cardRep.findById(cardVisa.getCardNumber())).thenReturn(Optional.of(cardVisa));
        when(cardRep.findById(cardAmex.getCardNumber())).thenReturn(Optional.of(cardAmex));
        when(cardRep.findById(cardNara.getCardNumber())).thenReturn(Optional.of(cardNara));
        Card testVisa = eldarService.findAndSetCard(1234567890L);
        Card testAmex = eldarService.findAndSetCard(9876543210L);
        Card testNara = eldarService.findAndSetCard(1111222333L);
        assertTrue(testVisa.toString().contains("fullName=" + cardVisa.getFullName()));
        assertTrue(testAmex.toString().contains("expireDate=" + cardAmex.getExpireDate()));
        assertTrue(testNara.toString().contains("brand=" + cardNara.getBrand()));
    }
}