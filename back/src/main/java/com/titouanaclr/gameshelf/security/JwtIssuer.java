package com.titouanaclr.gameshelf.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.titouanaclr.gameshelf.model.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties props;

    public String issue(int userId, String userEmail, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))) // TODO : make it last 5min and use refresh token
                .withClaim("e", userEmail)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(props.getSecretKey()));
    }
}
