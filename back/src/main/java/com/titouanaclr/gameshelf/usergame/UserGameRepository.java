package com.titouanaclr.gameshelf.usergame;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Integer> {

    public Page<UserGame> findByUserId(Pageable pageable, int userId);
}
