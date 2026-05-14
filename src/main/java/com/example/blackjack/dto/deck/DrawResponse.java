package com.example.blackjack.dto.deck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DrawResponse {
    @JsonProperty("cards")
    private List<Carta> cartas;
}