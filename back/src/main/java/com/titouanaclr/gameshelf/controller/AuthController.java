package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.LoginRequest;
import com.titouanaclr.gameshelf.model.LoginResponse;
import com.titouanaclr.gameshelf.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return this.authService.attemptLogin(request.getEmail(), request.getPassword());
    }

}
