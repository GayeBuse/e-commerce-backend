package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Card;
import com.workintech.ecommercebackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(long id) {
        return null;
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card delete(long id) {
        Card card = findById(id);
        if (card != null) {
            cardRepository.delete(card);
            return card;
        } else {
            return null; // hata mesajı gelecek
        }
    }
}
