package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.model.UserGameRequest;
import org.springframework.stereotype.Service;

@Service
public class UserGameMapper {

    public UserGame toUserGame(UserGameRequest request) {
        return UserGame.builder()
                .id(request.id())
                .user(request.user())
                .game(request.game())
                .location(request.location())
                .build();
    }
}
