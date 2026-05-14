package com.example.blackjack.service;

import com.example.blackjack.dto.deck.DeckResponse;
import com.example.blackjack.dto.deck.DrawResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeckService {

    private final RestTemplate restTemplate = new RestTemplate();

    public DeckResponse criarBaralho() {
        String url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
        return restTemplate.getForObject(url, DeckResponse.class);
    }

    public DrawResponse comprarCartas(String deckId, int quantidade) {
        String url = "https://deckofcardsapi.com/api/deck/" + deckId + "/draw/?count=" + quantidade;
        return restTemplate.getForObject(url, DrawResponse.class);
    }
}