package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Game;
import com.titouanaclr.gameshelf.model.GameCreateRequest;
import com.titouanaclr.gameshelf.model.GameResponse;
import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public Integer save(GameCreateRequest request) {
        Game game = gameMapper.toGame(request);
        return this.gameRepository.save(game).getId();
    }


    public GameResponse findById(Integer gameId) {
        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("No game found with ID " + gameId));
        return this.gameMapper.toGameResponse(game);
    }

    public PageResponse<GameResponse> findAllGames(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Game> games = this.gameRepository.findAll(pageable);
        List<GameResponse> gameResponse = games.stream()
                .map(gameMapper::toGameResponse)
                .toList();

        return new PageResponse<GameResponse>(
                gameResponse,
                games.getNumber(),
                games.getSize(),
                games.getTotalElements(),
                games.getTotalPages(),
                games.isFirst(),
                games.isLast()
        );
    }
}
