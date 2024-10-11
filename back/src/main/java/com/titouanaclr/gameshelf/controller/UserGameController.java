package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.model.UserGameRequest;
import com.titouanaclr.gameshelf.service.UserGameService;
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

    @GetMapping
    public ResponseEntity<PageResponse<UserGame>> findCurrentUserGames(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "9", required = false) int size,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(this.userGameService.findCurrentUserGames(page, size, currentUser));
    }

    @PostMapping
    public ResponseEntity<Integer> addUserGame(
            @RequestBody @Valid UserGameRequest request,
            Authentication currentUser
    ){
        return ResponseEntity.ok(this.userGameService.saveCurrentUserGame(request, currentUser));
    }

    @DeleteMapping("{user-game-id}")
    public ResponseEntity<Void> deleteUserGame(
            @PathVariable("user-game-id") Integer userGameId,
            Authentication currentUser
    ) {
        this.userGameService.deleteUserGame(userGameId, currentUser);
        return ResponseEntity.noContent().build();
    }
}
