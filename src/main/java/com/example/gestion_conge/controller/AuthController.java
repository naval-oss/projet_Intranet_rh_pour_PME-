package com.example.gestion_conge.controller;

import com.example.gestion_conge.serice.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/encode/{password}")
    public String encodePassword(@PathVariable String password) {
        return authService.encodePassword(password);
    }
}
