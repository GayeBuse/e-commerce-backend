package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findAllCards();
    Card findById(long id);
    Card save(Card card);
    Card delete(long id);
}
