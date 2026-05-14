document
    .getElementById("novo-jogo")
    .addEventListener("click", iniciarPartida);

document
    .getElementById("comprar")
    .addEventListener("click", pedirCarta);

document
    .getElementById("parar")
    .addEventListener("click", pararPartida);

document
    .getElementById("logout")
    .addEventListener("click", abrirModal);

document
    .getElementById("cancelar-sair")
    .addEventListener("click", fecharModal);

document
    .getElementById("confirmar-sair")
    .addEventListener("click", sairDoJogo);

document
    .getElementById("como-jogar")
    .addEventListener("click", abrirModalRegras);

document
    .getElementById("fechar-regras")
    .addEventListener("click", fecharModalRegras);


async function iniciarPartida() {

    try {

        const partida = await iniciarPartidaApi();

        renderizarPartida(partida);

    } catch (error) {

        atualizarStatus("Erro ao iniciar partida.");

        console.error(error);
    }
}


async function pedirCarta() {

    try {

        const partida = await comprarCartaApi();

        renderizarPartida(partida);

    } catch (error) {

        atualizarStatus("Erro ao comprar carta.");

        console.error(error);
    }
}


async function pararPartida() {

    try {

        const partida = await pararPartidaApi();

        renderizarPartida(partida);

    } catch (error) {

        atualizarStatus("Erro ao finalizar partida.");

        console.error(error);
    }
}

async function sairDoJogo() {

    try {

        await enviarAgradecimentoApi();

        localStorage.removeItem("token");

        window.location.href =
            "../auth/login.html";

    } catch (error) {

        atualizarStatus("Erro ao sair do jogo.");

        console.error(error);
    }
}