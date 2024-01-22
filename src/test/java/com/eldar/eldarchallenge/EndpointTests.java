package com.eldar.eldarchallenge;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import com.eldar.eldarchallenge.entities.Card;
import com.eldar.eldarchallenge.services.service.EldarService;
import com.eldar.eldarchallenge.services.command.ValidAndGetCardCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointTests {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public EldarService eldarService;
    @Autowired
    public ValidAndGetCardCommand getCommand;

    LocalDate date1;
    LocalDate date2;

    Card cardVisa;
    Card cardAmex;
    Card cardNara;

    @BeforeEach
    public void setup() {
        date1 = LocalDate.of(2025, Month.JANUARY, 1);
        date2 = LocalDate.of(2025, Month.MARCH, 1);

        cardVisa = new Card(1234567890L, "John Doe", java.sql.Date.valueOf(date1), "Visa");
        cardAmex = new Card(9876543210L, "Jane Smith", java.sql.Date.valueOf(date2), "Amex");
        cardNara = new Card(1111222333L, "Alice Johnson", new Date(), "Nara");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void case1() throws Exception {
        when(eldarService.isOperationValid(800)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.get("/1234567890")
                .param("amount", "1800")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void case2() throws Exception {
        when(eldarService.isOperationValid(800)).thenReturn(true);
        when(eldarService.findAndSetCard(9876543210L)).thenReturn(cardAmex);
        when(eldarService.isCardValid(cardAmex.getExpireDate())).thenReturn(true);
        when(eldarService.taxAmount(cardAmex.getBrand())).thenReturn(1.0);
        mockMvc.perform(MockMvcRequestBuilders.get("/9876543210")
                        .param("amount", "800")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void case3() throws Exception {
        when(eldarService.isOperationValid(800)).thenReturn(true);
        when(eldarService.findAndSetCard(1111222333L)).thenReturn(cardNara);
        when(eldarService.isCardValid(cardNara.getExpireDate())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.get("/1111222333")
                        .param("amount", "800")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

}
