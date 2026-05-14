package com.example.blackjack.service;

import com.example.blackjack.dto.deck.Carta;
import com.example.blackjack.dto.stats.Estatisticas;
import com.example.blackjack.entity.Partida;
import com.example.blackjack.entity.enums.StatusPartida;
import com.example.blackjack.repository.PartidaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final DeckService deckService;
    private final PartidaRepository partidaRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public Partida iniciar(String email) {
        verificarPartidaEmAndamento(email);
        Partida partida = partidaRepository.save(
                Partida.builder()
                        .emailUsuario(email)
                        .status(StatusPartida.EM_ANDAMENTO)
                        .deckId(deckService.criarBaralho().getDeckId())
                        .build());
        List<Carta> jogador = deckService.comprarCartas(partida.getDeckId(), 2).getCartas();
        List<Carta> dealer = deckService.comprarCartas(partida.getDeckId(), 2).getCartas();
        partida.setJogadorCartasJson(toJson(jogador));
        partida.setDealerCartasJson(toJson(dealer));
        atualizarPontuacao(partida);
        return partidaRepository.save(partida);
    }

    public Partida comprar(String email) {
        Partida partida = getPartidaAtiva(email);
        List<Carta> cartas = fromJson(partida.getJogadorCartasJson());
        cartas.addAll(deckService.comprarCartas(partida.getDeckId(), 1).getCartas());
        partida.setJogadorCartasJson(toJson(cartas));
        atualizarPontuacao(partida);
        if (partida.getJogadorPontuacao() == 0) {
            partida.setStatus(StatusPartida.DERROTA);
        }
        return partidaRepository.save(partida);
    }

    public Partida parar(String email) {
        Partida partida = getPartidaAtiva(email);
        List<Carta> dealer = fromJson(partida.getDealerCartasJson());
        while (calcularPontos(dealer) < 17 && calcularPontos(dealer) > 0) {
            dealer.addAll(deckService.comprarCartas(partida.getDeckId(), 1).getCartas());
        }
        partida.setDealerCartasJson(toJson(dealer));
        atualizarPontuacao(partida);
        int jogador = partida.getJogadorPontuacao();
        int dealerPts = partida.getDealerPontuacao();

        if (dealerPts == 0 || jogador > dealerPts) {
            partida.setStatus(StatusPartida.VITORIA);
        } else if (jogador < dealerPts) {
            partida.setStatus(StatusPartida.DERROTA);
        } else {
            partida.setStatus(StatusPartida.EMPATE);
        }

        return partidaRepository.save(partida);
    }

    private Partida getPartidaAtiva(String email) {
        return partidaRepository.findByEmailUsuarioAndStatus(email, StatusPartida.EM_ANDAMENTO).orElseThrow(() -> new RuntimeException("Nenhuma partida ativa"));
    }

    private void atualizarPontuacao(Partida partida) {
        List<Carta> jogador = fromJson(partida.getJogadorCartasJson());
        List<Carta> dealer = fromJson(partida.getDealerCartasJson());
        partida.setJogadorPontuacao(calcularPontos(jogador));
        partida.setDealerPontuacao(calcularPontos(dealer));
    }

    private int calcularPontos(List<Carta> cartas) {
        int total = 0;
        int ases = 0;

        for (Carta carta : cartas) {
            switch (carta.getValor()) {
                case "ACE" -> {
                    total += 11;
                    ases++;
                }
                case "KING", "QUEEN", "JACK" -> total += 10;
                default -> total += Integer.parseInt(carta.getValor());
            }
        }

        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }
        return total > 21 ? 0 : total;
    }

    private String toJson(List<Carta> cartas) {
        try {
            return mapper.writeValueAsString(cartas);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar cartas");
        }
    }

    private List<Carta> fromJson(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<Carta>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar cartas");
        }
    }

    public Estatisticas getEstatisticas(String email) {
        int vitorias = partidaRepository.countByEmailUsuarioAndStatus(email, StatusPartida.VITORIA);
        int derrotas = partidaRepository.countByEmailUsuarioAndStatus(email, StatusPartida.DERROTA);
        int empates = partidaRepository.countByEmailUsuarioAndStatus(email, StatusPartida.EMPATE);
        Estatisticas estatisticas = new Estatisticas(vitorias, derrotas, empates);
        return estatisticas;
    }

    public void verificarPartidaEmAndamento(String email) {
        Optional<Partida> ativa = partidaRepository.findByEmailUsuarioAndStatus(email, StatusPartida.EM_ANDAMENTO);
        if (ativa.isPresent()) {
            Partida antiga = ativa.get();
            antiga.setStatus(StatusPartida.DERROTA);
            partidaRepository.save(antiga);
        }
    }
}