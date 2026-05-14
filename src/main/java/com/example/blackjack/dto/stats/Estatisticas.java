package com.example.blackjack.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Estatisticas {
    private int vitorias;
    private int derrotas;
    private int empates;
}
