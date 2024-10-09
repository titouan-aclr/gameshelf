package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.model.UserProfileResponse;
import com.titouanaclr.gameshelf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID :" + userId));
        return this.userMapper.toUserProfileResponse(user);
    }
}
