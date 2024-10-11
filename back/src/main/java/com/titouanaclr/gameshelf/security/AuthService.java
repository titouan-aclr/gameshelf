package com.titouanaclr.gameshelf.security;

import com.titouanaclr.gameshelf.role.ERole;
import com.titouanaclr.gameshelf.role.Role;
import com.titouanaclr.gameshelf.role.RoleService;
import com.titouanaclr.gameshelf.user.UserService;
import com.titouanaclr.gameshelf.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserService userService;

    public LoginResponse attemptLogin(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

    public void register(RegisterRequest request) {
        Role userRole = roleService.findByName(ERole.ROLE_USER)
                // TODO : better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE_USER was not initialized"));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(userRole))
                .build();

        userService.save(user);
    }
}
