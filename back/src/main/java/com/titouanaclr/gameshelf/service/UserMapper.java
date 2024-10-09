package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.model.UserProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserProfileResponse toUserProfileResponse(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .build();
    }
}
