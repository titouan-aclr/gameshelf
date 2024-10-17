package com.titouanaclr.gameshelf.user;

import com.titouanaclr.gameshelf.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public UserProfileResponse findUserProfileById(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID :" + userId));
        return this.userMapper.toUserProfileResponse(user);
    }

    @Transactional
    public UserAccountResponse findCurrentUser(Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        User user = this.userRepository.findById(userPrincipal.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Error with connected user"));

        // TODO : find a solution to avoid fetching games
        Hibernate.initialize(user.getLocations());
        Hibernate.initialize(user.getGames());
        return userMapper.toUserAccountResponse(user);
    }

    public User findById(int userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID :" + userId));
    }
}
