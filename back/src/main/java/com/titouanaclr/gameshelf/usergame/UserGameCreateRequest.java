package com.titouanaclr.gameshelf.usergame;

import com.titouanaclr.gameshelf.game.Game;
import com.titouanaclr.gameshelf.location.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request object for creating a new user game entry in the inventory.")
public record UserGameCreateRequest(
        @Schema(description = "The game associated with the user game entry")
        @NotNull(message = "300")
        Game game,
        @Schema(description = "The location where the game is stored or played")
        Location location
) { }
