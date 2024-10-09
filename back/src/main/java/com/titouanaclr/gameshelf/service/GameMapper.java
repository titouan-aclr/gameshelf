package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Game;
import com.titouanaclr.gameshelf.model.GameRequest;
import org.springframework.stereotype.Service;

@Service
public class GameMapper {
    public Game toGame(GameRequest request) {
        return Game.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .minPlayers(request.minPlayers())
                .maxPlayers(request.maxPlayers())
                .playingTime(request.playingTime())
                .yearPublished(request.yearPublished())
                .imageUrl(request.imageUrl())
                .thumbnailUrl(request.thumbnailUrl())
                .categories(request.categories())
                .build();
    }
}
