package com.workintech.ecommercebackend.service;

import com.workintech.ecommercebackend.entity.Card;
import com.workintech.ecommercebackend.exceptions.GlobalExceptions;
import com.workintech.ecommercebackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
            throw new GlobalExceptions("Card by given id cannot be found: "+id, HttpStatus.NOT_FOUND);
        }
    }
}
