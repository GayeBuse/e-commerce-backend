package com.workintech.ecommercebackend.repository;

import com.workintech.ecommercebackend.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
