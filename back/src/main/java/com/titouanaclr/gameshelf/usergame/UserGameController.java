package com.titouanaclr.gameshelf.usergame;

import com.titouanaclr.gameshelf.common.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User's Games", description = "Manage user's game inventory")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping("users/current/games")
public class UserGameController {

    private final UserGameService userGameService;

    @Operation(summary = "Get user's games from his inventory", description = "Retrieve a paginated list of games owned by the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of user's games", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<PageResponse<UserGameResponse>> findCurrentUserGames(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "9", required = false) int size,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(this.userGameService.findCurrentUserGames(page, size, currentUser));
    }

    @Operation(summary = "Add a game to the user's inventory", description = "Add a new game to the current user's game inventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the game to the user's inventory", content = @Content(mediaType = "application/json", schema = @Schema(type = "integer", example = "93"))),
            @ApiResponse(responseCode = "400", description = "Bad request - Validation failed", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Integer> addUserGame(
            @RequestBody @Valid UserGameCreateRequest request,
            Authentication currentUser
    ){
        return ResponseEntity.ok(this.userGameService.saveCurrentUserGame(request, currentUser));
    }

    @Operation(summary = "Delete a game from the user's inventory", description = "Remove a game from the current user's game inventory by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the game", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - Game does not exist", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("{user-game-id}")
    public ResponseEntity<Void> deleteUserGame(
            @Parameter(description = "ID of the game to be deleted", example = "17")
            @PathVariable("user-game-id") Integer userGameId,
            Authentication currentUser
    ) {
        this.userGameService.deleteUserGame(userGameId, currentUser);
        return ResponseEntity.noContent().build();
    }
}
