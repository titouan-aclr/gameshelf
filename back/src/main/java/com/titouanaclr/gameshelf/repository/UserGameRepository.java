package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.UserGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends CrudRepository<UserGame, Integer> {
}
