package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.model.UserGameCreateRequest;
import com.titouanaclr.gameshelf.model.UserGameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGameMapper {

    private final GameMapper gameMapper;
    private final LocationMapper locationMapper;

    public UserGame toUserGame(UserGameCreateRequest request, User user) {
        return UserGame.builder()
                .user(user)
                .game(request.game())
                .location(request.location())
                .build();
    }

    public UserGameResponse toUserGameResponse(UserGame userGame) {
        return UserGameResponse.builder()
                .id(userGame.getId())
                .game(this.gameMapper.toGameResponse(userGame.getGame()))
                .location(this.locationMapper.toLocationResponse(userGame.getLocation()))
                .registeredDate(userGame.getRegisteredDate())
                .build();
    }
}
