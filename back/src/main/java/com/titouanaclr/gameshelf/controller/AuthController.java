package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.LoginRequest;
import com.titouanaclr.gameshelf.model.LoginResponse;
import com.titouanaclr.gameshelf.model.RegisterRequest;
import com.titouanaclr.gameshelf.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user", description = "Registers a new user and returns a 202 ACCEPTED status.")
    @ApiResponse(responseCode = "202", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid registration details")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> registerAndLogin(@RequestBody @Valid RegisterRequest request) {
        this.authService.register(request);
        // TODO : add email validation
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Login", description = "Authenticates a user and returns a JWT token.")
    @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned", content = @Content(schema = @Schema(implementation = LoginResponse.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid login credentials", content = @Content())
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return this.authService.attemptLogin(request.getEmail(), request.getPassword());
    }

}
