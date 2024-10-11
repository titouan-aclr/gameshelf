package com.titouanaclr.gameshelf.game;

import com.titouanaclr.gameshelf.category.CategoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response object representing a game")
public class GameResponse {
    @Schema(description = "The unique identifier of the game", example = "31")
    private Integer id;
    @Schema(description = "The name of the game", example = "Monopoly")
    private String name;
    @Schema(description = "A exhaustive description of game, including the goal and the main rules.", example = "Theme&amp;#10;Players take the part of land owners, attempting to buy and then...")
    private String description;
    @Schema(description = "URL to the game's image", example = "https://example.com/game-image.jpg")
    private String imageUrl;
    @Schema(description = "URL to the game's thumbnail image", example = "https://example.com/game-thumbnail.jpg")
    private String thumbnailUrl;
    @Schema(description = "Minimum number of players required to play the game", example = "2")
    private int minPlayers;
    @Schema(description = "Maximum number of players allowed to play the game", example = "8")
    private int maxPlayers;
    @Schema(description = "Estimated playing time in minutes", example = "180")
    private int playingTime;
    @Schema(description = "Year the game was published", example = "1935")
    private int yearPublished;
    @Schema(description = "List of categories the game belongs to")
    private List<CategoryResponse> categories;
}
