package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.Game;
import com.titouanaclr.gameshelf.model.GameRequest;
import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid GameRequest request) {
        return ResponseEntity.ok(this.gameService.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<Game>> findAllGames(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "9", required = false) int size
    ) {
        return ResponseEntity.ok(this.gameService.findAllGames(page, size));
    }

    @GetMapping("{game-id}")
    public ResponseEntity<Game> findBookById(@PathVariable("game-id") Integer gameId) {
        return ResponseEntity.ok(this.gameService.findById(gameId));
    }


}