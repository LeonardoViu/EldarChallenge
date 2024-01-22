package com.eldar.eldarchallenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    private Long cardNumber;
    @Column
    private String fullName;
    @Column

    private Date expireDate;
    @Column
    private String brand;

}
