package com.example.blackjack.controller;

import com.example.blackjack.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@CrossOrigin
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @PostMapping("/agradecimento")
    public void enviarAgradecimento(Authentication authentication) {
        emailSenderService.enviarEmailAgradecimento(authentication.getName());
    }
}