package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
