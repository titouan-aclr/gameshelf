package com.titouanaclr.gameshelf.security;

import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Our UserDetails' username is actually our user's email in the database
        System.out.println("CustomUserDetailService::loadUserByUsername");
        User user = this.userService.findByEmail(username).orElseThrow();
        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))) // TODO : use proper roles
                .password(user.getPassword())
                .build();
    }
}
