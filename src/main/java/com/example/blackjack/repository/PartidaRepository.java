package com.example.blackjack.repository;

import com.example.blackjack.entity.Partida;
import com.example.blackjack.entity.enums.StatusPartida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    Optional<Partida> findByEmailUsuarioAndStatus(String emailUsuario, StatusPartida status);

    int countByEmailUsuarioAndStatus(String email, StatusPartida status);
}