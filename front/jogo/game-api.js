const GAME_API_URL = "http://localhost:8080/api";


async function iniciarPartidaApi() {
    const response = await fetch(`${GAME_API_URL}/jogo/iniciar`, {
        method: "POST",

        headers: getAuthHeaders()
    });

    if (!response.ok) {
        throw new Error("Erro ao iniciar partida");
    }
    return await response.json();
}


async function comprarCartaApi() {
    const response = await fetch(`${GAME_API_URL}/jogo/comprar`, {
        method: "POST",

        headers: getAuthHeaders()
    });

    if (!response.ok) {
        throw new Error("Erro ao comprar carta");
    }
    return await response.json();
}


async function pararPartidaApi() {
    const response = await fetch(`${GAME_API_URL}/jogo/parar`, {
        method: "POST",

        headers: getAuthHeaders()
    });

    if (!response.ok) {
        throw new Error("Erro ao parar partida");
    }
    return await response.json();
}

async function enviarAgradecimentoApi() {

    const response = await fetch(`${GAME_API_URL}/email/agradecimento`, {
        method: "POST",

        headers: getAuthHeaders()
    });

    if (!response.ok) {
        throw new Error("Erro ao sair do jogo");
    }
}