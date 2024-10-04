package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
}
