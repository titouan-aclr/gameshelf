package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.ERole;
import com.titouanaclr.gameshelf.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Optional<Role> findByName(ERole role);
}
