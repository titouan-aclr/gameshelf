package com.titouanaclr.gameshelf.common;

import com.titouanaclr.gameshelf.security.UserPrincipal;
import com.titouanaclr.gameshelf.security.UserPrincipalAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;
import java.util.List;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        List<SimpleGrantedAuthority> authorities = Arrays.stream(annotation.authorithies())
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserPrincipal principal = UserPrincipal.builder()
                .userId(annotation.userId())
                .email("fake@email.com")
                .authorities(authorities)
                .build();

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UserPrincipalAuthenticationToken(principal));

        return context;
    }
}
