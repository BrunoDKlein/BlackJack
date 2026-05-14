package com.example.blackjack.dto.deck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeckResponse {
    @JsonProperty("deck_id")
    private String deckId;
}