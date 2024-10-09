package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.repository.UserGameRepository;
import com.titouanaclr.gameshelf.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameService {

    @Autowired
    private UserGameRepository userGameRepository;

    public PageResponse<UserGame> findCurrentUserGames(int page, int size, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());

        Pageable pageable = PageRequest.of(page, size, Sort.by("registeredDate").descending());
        Page<UserGame> userGames = this.userGameRepository.findByUserId(pageable, userPrincipal.getUserId());
        List<UserGame> userGamesResponse = userGames.stream().toList();

        return new PageResponse<UserGame>(
                userGamesResponse,
                userGames.getNumber(),
                userGames.getSize(),
                userGames.getTotalElements(),
                userGames.getTotalPages(),
                userGames.isFirst(),
                userGames.isLast()
        );
    }
}
