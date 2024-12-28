package com.example.Book_Shopping.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book_Shopping.model.AuthResponse;
import com.example.Book_Shopping.model.LoginRequestDto;
import com.example.Book_Shopping.model.RegisterRequestDto;
import com.example.Book_Shopping.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService; 
    
    public AuthController(AuthService authService) {
    	this.authService = authService;
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequestDto registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        return authService.loginUser(loginRequest);
    }
}
