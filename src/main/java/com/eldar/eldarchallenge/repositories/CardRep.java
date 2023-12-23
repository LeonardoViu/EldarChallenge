package com.eldar.eldarchallenge.repositories;

import com.eldar.eldarchallenge.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRep extends JpaRepository<Card, Long> {

}
