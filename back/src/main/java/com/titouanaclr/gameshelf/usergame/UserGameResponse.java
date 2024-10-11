package com.titouanaclr.gameshelf.usergame;

import com.titouanaclr.gameshelf.game.GameResponse;
import com.titouanaclr.gameshelf.location.LocationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response object representing a user's game entry in their inventory.")
public class UserGameResponse {
    @Schema(description = "The unique identifier of the user game entry", example = "83")
    private Integer id;
    @Schema(description = "Details of the game associated with the user game entry")
    private GameResponse game;
    @Schema(description = "Details of the location where the game is stored")
    private LocationResponse location;
    @Schema(description = "The date when the game was registered to the user's inventory", example = "2023-02-06T10:00:00Z")
    private Date registeredDate;
}
