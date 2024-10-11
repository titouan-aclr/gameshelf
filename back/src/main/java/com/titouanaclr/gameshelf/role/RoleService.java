package com.titouanaclr.gameshelf.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(ERole role) {
        return this.roleRepository.findByName(role);
    }
}
