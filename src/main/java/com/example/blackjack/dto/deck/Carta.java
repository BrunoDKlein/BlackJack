package com.example.blackjack.dto.deck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Carta {
    @JsonProperty("value")
    private String valor;
    @JsonProperty("suit")
    private String naipe;
    @JsonProperty("image")
    private String imagem;
}