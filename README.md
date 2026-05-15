# ♠ ♥ BLACKJACK 21 ♦ ♣

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![JWT](https://img.shields.io/badge/JWT-Autenticação-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![REST API](https://img.shields.io/badge/API-REST-success)
![Spring Security](https://img.shields.io/badge/Security-Spring_Security-darkgreen)

Projeto full-stack de Blackjack desenvolvido com Java + Spring Boot, utilizando arquitetura REST, autenticação JWT, persistência em banco de dados PostgreSQL e integração com API externa.

---

# Descrição do Projeto

Este projeto consiste em um sistema completo de Blackjack 21 com backend desenvolvido com Java + Spring Boot e frontend web interativo.

A aplicação realiza:

* autenticação de usuários;
* gerenciamento de partidas;
* persistência de dados;
* envio automático de emails HTML;
* estatísticas de desempenho;
* integração com API externa de cartas.

---

# Funcionalidades

### Sistema de autenticação

* Cadastro de usuários
* Login com JWT
* Criptografia de senha com BCrypt
* Rotas protegidas

### Sistema do jogo

* Iniciar partida
* Comprar cartas
* Parar rodada
* Dealer automático
* Pontuação real de BlackJack
* Tratamento inteligente do Ás
* Detecção de derrota automática
* Persistência das partidas no banco

### Estatísticas

* Controle de vitória, derrota e empate por jogador

### Email automático

* Envio de email HTML estilizado
* Resumo de desempenho do jogador
* Layout visual sofisticado

---

# Tecnologias Utilizadas

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT
* Lombok
* Java Mail Sender
* PostgreSQL
* Hibernate
* Maven

### Frontend

* HTML
* CSS
* JavaScript

### APIs e Serviços

* Deck Of Cards API
* Gmail SMTP

---

# Destaques Técnicos

###  Autenticação JWT

O sistema utiliza autenticação baseada em JSON Web Token (JWT), amplamente utilizada em aplicações modernas e APIs REST.

Funcionalidades implementadas:

* geração de token;
* validação de token;
* proteção de rotas;
* autenticação stateless.

### Spring Security

Integração completa com Spring Security para controle de autenticação e autorização.

### API REST

Toda a comunicação do sistema é baseada em arquitetura REST.

Foram implementados endpoints para:

* autenticação;
* gerenciamento do jogo;
* envio de emails.

### Persistência com PostgreSQL

As partidas são armazenadas no banco PostgreSQL utilizando JPA/Hibernate.

### Integração com API Externa

O projeto realiza integração com a Deck Of Cards API para gerenciamento de baralhos e compra de cartas.

### Envio de Email HTML

Implementação de envio automático de emails HTML personalizados utilizando Java Mail Sender e SMTP do Gmail.

### Frontend Interativo

O frontend possui:

* efeito de distribuição de cartas;
* glow dinâmico;
* interface inspirada em cassino.
* responsividade

---

# Como Executar o Projeto

```bash
git clone git@github.com:BrunoDKlein/BlackJack.git
```

Configure as variáveis de ambiente:

* DB_PASSWORD=suaSenhaBancoDeDados
* EMAIL_ORIGEM=seuEmail@gmail.com
* EMAIL_PASSWORD=suaSenhaDeApp
  
E execute o projeto pela IDE ou com:

```bash
./mvnw spring-boot:run
```

---

# Endpoints Principais

```http
POST /api/auth/register
POST /api/auth/login
POST /api/jogo/iniciar
POST /api/jogo/comprar
POST /api/jogo/parar
POST /api/email/agradecimento

```

---

# Autor

Desenvolvido por Bruno Klein.
