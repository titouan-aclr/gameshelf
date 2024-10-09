package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Integer> {
}
