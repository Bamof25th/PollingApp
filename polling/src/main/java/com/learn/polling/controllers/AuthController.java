package com.learn.polling.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.polling.domain.User;
import com.learn.polling.domain.dtos.JwtAuthenticationResponse;
import com.learn.polling.domain.dtos.LoginRequest;
import com.learn.polling.domain.dtos.SignUpRequest;
import com.learn.polling.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = authService.login(loginRequest);

        JwtAuthenticationResponse jwtToken = new JwtAuthenticationResponse(jwt, "Bearer");

        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<?> userSignup(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = authService.signUp(signUpRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
