package com.titouanaclr.gameshelf.security;

import com.titouanaclr.gameshelf.model.Role;
import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Our UserDetails' username is actually our user's email in the database
        User user = this.userService.findByEmail(username).orElseThrow();

        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(authorities)
                .password(user.getPassword())
                .build();
    }
}
