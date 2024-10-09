package com.titouanaclr.gameshelf.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GameRequest(
        int id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String name,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String description,
        @NotNull(message = "102")
        int minPlayers,
        @NotNull(message = "103")
        int maxPlayers,
        @NotNull(message = "104")
        int playingTime,
        @NotNull(message = "105")
        int yearPublished,
        String imageUrl,
        String thumbnailUrl,
        List<Category> categories
) {

}
