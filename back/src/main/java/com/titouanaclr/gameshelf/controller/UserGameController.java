package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.model.UserGameRequest;
import com.titouanaclr.gameshelf.service.UserGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserGameController {

    private final UserGameService userGameService;

    @GetMapping("/users/current/games")
    public ResponseEntity<PageResponse<UserGame>> findCurrentUserGames(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "9", required = false) int size,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(this.userGameService.findCurrentUserGames(page, size, currentUser));
    }

    @PostMapping("users/current/games")
    public ResponseEntity<Integer> addUserGame(
            @RequestBody @Valid UserGameRequest request,
            Authentication currentUser
    ){
        return ResponseEntity.ok(this.userGameService.saveCurrentUserGame(request, currentUser));
    }
}
