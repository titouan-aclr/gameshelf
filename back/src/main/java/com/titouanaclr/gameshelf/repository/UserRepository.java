package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
