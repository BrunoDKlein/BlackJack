package com.example.blackjack.entity;

import com.example.blackjack.entity.enums.StatusPartida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailUsuario;

    private String deckId;

    private int jogadorPontuacao;

    private int dealerPontuacao;

    @Enumerated(EnumType.STRING)
    private StatusPartida status;

    @Column(columnDefinition = "TEXT")
    private String jogadorCartasJson;

    @Column(columnDefinition = "TEXT")
    private String dealerCartasJson;
}
