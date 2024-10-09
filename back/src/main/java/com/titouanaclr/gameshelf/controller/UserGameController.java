package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.service.UserGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
