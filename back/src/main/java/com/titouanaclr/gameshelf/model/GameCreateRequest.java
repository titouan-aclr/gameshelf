package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Request object for creating a new game")
public record GameCreateRequest(
        @Schema(description = "The name of the game", example = "Monopoly")
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        @NotBlank(message = "100")
        String name,
        @Schema(description = "A exhaustive description of game, including the goal and the main rules.", example = "Theme&amp;#10;Players take the part of land owners, attempting to buy and then...")
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        @NotBlank(message = "101")
        String description,
        @Schema(description = "Minimum number of players required to play the game", example = "2")
        @NotNull(message = "102")
        int minPlayers,
        @Schema(description = "Maximum number of players allowed to play the game", example = "8")
        @NotNull(message = "103")
        int maxPlayers,
        @Schema(description = "Estimated playing time in minutes", example = "180")
        @NotNull(message = "104")
        int playingTime,
        @Schema(description = "Year the game was published", example = "1935")
        @NotNull(message = "105")
        int yearPublished,
        @Schema(description = "URL to the game's image", example = "https://example.com/game-image.jpg")
        String imageUrl,
        @Schema(description = "URL to the game's thumbnail image", example = "https://example.com/game-thumbnail.jpg")
        String thumbnailUrl,
        @Schema(description = "List of categories the game belongs to. Must be existing categories")
        List<Category> categories
) { }
