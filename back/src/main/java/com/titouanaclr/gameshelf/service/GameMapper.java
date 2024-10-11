package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Game;
import com.titouanaclr.gameshelf.model.GameCreateRequest;
import com.titouanaclr.gameshelf.model.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GameMapper {

    private final CategoryMapper categoryMapper;

    public Game toGame(GameCreateRequest request) {
        return Game.builder()
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

    public GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .imageUrl(game.getImageUrl())
                .thumbnailUrl(game.getThumbnailUrl())
                .minPlayers(game.getMinPlayers())
                .maxPlayers(game.getMaxPlayers())
                .playingTime(game.getPlayingTime())
                .yearPublished(game.getYearPublished())
                .categories(game.getCategories().stream().map(categoryMapper::toCategoryResponse).toList())
                .build();
    }
}
