function getToken() {
    return localStorage.getItem("token");
}


function usuarioAutenticado() {
    return Boolean(getToken());
}


function protegerPagina() {
    if (!usuarioAutenticado()) {
        window.location.href = "../auth/login.html";
    }
}


function getAuthHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getToken()}`
    };
}


function logout() {
    localStorage.removeItem("token");
    window.location.href = "../auth/login.html";
}