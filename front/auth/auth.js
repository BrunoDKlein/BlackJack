const API_URL = "http://localhost:8080/api/auth";

const loginForm = document.getElementById("login-form");
const registerForm = document.getElementById("register-form");


// LOGIN

if (loginForm) {

    loginForm.addEventListener("submit", async (event) => {

        event.preventDefault();

        const email = document.getElementById("email").value;
        const senha = document.getElementById("senha").value;

        try {
            const response = await fetch(`${API_URL}/login`, {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    email,
                    senha
                })
            });

            if (!response.ok) {

                const erro = await response.text();

                throw new Error(erro);
            }

            const data = await response.json();

            localStorage.setItem("token", data.token);

            window.location.href = "../jogo/jogo.html";

        } catch (error) {
            document.getElementById("erro").textContent = "Email ou senha inválidos";
        }
    });
}


// CADASTRO

if (registerForm) {
    registerForm.addEventListener("submit", async (event) => {

        event.preventDefault();

        const nome = document.getElementById("nome").value;
        const email = document.getElementById("email").value;
        const senha = document.getElementById("senha").value;

        try {

            const response = await fetch(`${API_URL}/register`, {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    nome,
                    email,
                    senha
                })
            });

            if (!response.ok) {

                const erro = await response.text();

                throw new Error(erro);
            }

            window.location.href = "login.html";

        } catch (error) {
            document.getElementById("erro").textContent = "Erro ao criar conta";
        }
    });
}