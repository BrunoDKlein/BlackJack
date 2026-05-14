package com.example.blackjack.controller;

import com.example.blackjack.entity.Partida;
import com.example.blackjack.service.JogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jogo")
@RequiredArgsConstructor
@CrossOrigin
public class JogoController {

    private final JogoService jogoService;

    @PostMapping("/iniciar")
    public Partida iniciar(Authentication auth) {
        return jogoService.iniciar(auth.getName());
    }

    @PostMapping("/comprar")
    public Partida comprar(Authentication auth) {
        return jogoService.comprar(auth.getName());
    }

    @PostMapping("/parar")
    public Partida parar(Authentication auth) {
        return jogoService.parar(auth.getName());
    }
}