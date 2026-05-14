package com.example.blackjack.controller;

import com.example.blackjack.dto.auth.AuthRequest;
import com.example.blackjack.dto.auth.AuthResponse;
import com.example.blackjack.entity.Usuario;
import com.example.blackjack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return authService.registrar(usuario);
    }
}