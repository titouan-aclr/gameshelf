package com.titouanaclr.gameshelf.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    @Schema(description = "JWT token returned after successful login", example = "eyJhbGciOiJIUzI1NiIsIn...")
    private final String accessToken;
}
