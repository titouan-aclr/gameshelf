package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.LoginRequest;
import com.titouanaclr.gameshelf.model.LoginResponse;
import com.titouanaclr.gameshelf.model.RegisterRequest;
import com.titouanaclr.gameshelf.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> registerAndLogin(@RequestBody @Valid RegisterRequest request) {
        this.authService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return this.authService.attemptLogin(request.getEmail(), request.getPassword());
    }

}
