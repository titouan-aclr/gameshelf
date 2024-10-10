package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.exception.UnauthorizedActionException;
import com.titouanaclr.gameshelf.model.PageResponse;
import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.model.UserGame;
import com.titouanaclr.gameshelf.model.UserGameRequest;
import com.titouanaclr.gameshelf.repository.UserGameRepository;
import com.titouanaclr.gameshelf.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserGameMapper userGameMapper;
    private final UserService userService;

    @Transactional
    public PageResponse<UserGame> findCurrentUserGames(int page, int size, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());

        Pageable pageable = PageRequest.of(page, size, Sort.by("registeredDate").descending());
        Page<UserGame> userGames = this.userGameRepository.findByUserId(pageable, userPrincipal.getUserId());
        userGames.forEach(userGame -> Hibernate.initialize(userGame.getLocation()));
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

    public Integer saveCurrentUserGame(UserGameRequest request, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        if(request.user() == null) {
            User user = this.userService.findById(userPrincipal.getUserId());
            request = request.withUser(user);
        }

        UserGame userGame = userGameMapper.toUserGame(request);
        return this.userGameRepository.save(userGame).getId();
    }

    public void deleteUserGame(Integer userGameId, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());

        UserGame userGame = this.userGameRepository.findById(userGameId)
                .orElseThrow(() -> new EntityNotFoundException("UserGame not found with ID " + userGameId));

        if(!userGame.getUser().getId().equals(userPrincipal.getUserId())){
            throw new UnauthorizedActionException("UserGame with ID " + userGameId + " does not belong to current user");
        }

        this.userGameRepository.delete(userGame);
    }
}
