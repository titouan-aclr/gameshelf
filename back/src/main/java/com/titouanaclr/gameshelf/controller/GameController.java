package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.GameCreateRequest;
import com.titouanaclr.gameshelf.model.GameResponse;
import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Games", description = "Manage all games available for the application")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @Operation(summary = "Create a new game", description = "Save a new game to the application. Only accessible by admin users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created game", content = @Content(mediaType = "application/json", schema = @Schema(type = "integer", example = "32"))),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid game data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("admin/games")
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid GameCreateRequest request) {
        return ResponseEntity.ok(this.gameService.save(request));
    }

    @Operation(summary = "Get all games", description = "Retrieve a paginated list of all games available in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of games", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("games")
    public ResponseEntity<PageResponse<GameResponse>> findAllGames(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "9", required = false) int size
    ) {
        return ResponseEntity.ok(this.gameService.findAllGames(page, size));
    }

    @Operation(summary = "Get a game by ID", description = "Retrieve the details of a game using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved game", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameResponse.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("games/{game-id}")
    public ResponseEntity<GameResponse> findBookById(@PathVariable("game-id") Integer gameId) {
        return ResponseEntity.ok(this.gameService.findById(gameId));
    }
}
