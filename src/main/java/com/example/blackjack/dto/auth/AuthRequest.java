package com.example.blackjack.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String senha;
}