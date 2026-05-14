function atualizarStatus(texto) {
    document.getElementById("status").textContent = texto;
}


function atualizarPontuacaoJogador(pontos) {
    document.getElementById("jogador-pontuacao").textContent = `${pontos} pontos`;
}


function atualizarPontuacaoDealer(pontos) {
    document.getElementById("dealer-pontuacao").textContent = `${pontos} pontos`;
}


function renderizarCartas(containerId, cartas, ocultarPrimeira = false) {
    const container = document.getElementById(containerId);

    container.innerHTML = "";

    cartas.forEach((carta, index) => {
        const imagem = document.createElement("img");

        if (index === 0 && ocultarPrimeira) {

            imagem.src = "https://deckofcardsapi.com/static/img/back.png";

            imagem.alt = "Carta Oculta";

        } else {
            imagem.src = carta.image;

            imagem.alt = `${carta.value} de ${carta.suit}`;
        }
        container.appendChild(imagem);
    });
}


function controlarBotoes(status) {
    const emAndamento = status === "EM_ANDAMENTO";

    document.getElementById("comprar").disabled = !emAndamento;

    document.getElementById("parar").disabled = !emAndamento;
}


function traduzirStatus(status) {
    switch (status) {
        case "EM_ANDAMENTO":
            return "Sua vez.";

        case "VITORIA":
            return "Você venceu!";

        case "DERROTA":
            return "Dealer venceu!";

        case "EMPATE":
            return "Empate!";

        default:
            return "";
    }
}


function renderizarPartida(partida) {

    const jogadorCartas = JSON.parse(partida.jogadorCartasJson || "[]");
    const dealerCartas = JSON.parse(partida.dealerCartasJson || "[]");

    renderizarCartas("jogador-cartas", jogadorCartas);

    renderizarCartas("dealer-cartas", dealerCartas, partida.status === "EM_ANDAMENTO");

    atualizarPontuacaoJogador(partida.jogadorPontuacao);

    atualizarPontuacaoDealer(partida.status === "EM_ANDAMENTO" ? "??" : partida.dealerPontuacao);

    atualizarStatus(traduzirStatus(partida.status));

    controlarBotoes(partida.status);
}


function abrirModal() {
    document.getElementById("modal").style.display = "flex";
}

function fecharModal() {
    document.getElementById("modal").style.display = "none";
}

function abrirModalRegras() {
    document.getElementById("modal-regras").style.display = "flex";
}

function fecharModalRegras() {
    document.getElementById("modal-regras").style.display = "none";
}