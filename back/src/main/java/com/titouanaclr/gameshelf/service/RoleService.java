package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.ERole;
import com.titouanaclr.gameshelf.model.Role;
import com.titouanaclr.gameshelf.repository.RoleRepository;
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
