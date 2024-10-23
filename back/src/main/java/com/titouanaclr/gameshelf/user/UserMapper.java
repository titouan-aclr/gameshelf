package com.titouanaclr.gameshelf.user;

import com.titouanaclr.gameshelf.location.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserMapper {

    private final LocationMapper locationMapper;

    public UserProfileResponse toUserProfileResponse(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .build();
    }

    public UserAccountResponse toUserAccountResponse(User user) {
        return UserAccountResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .locations(user.getLocations().stream().map(locationMapper::toLocationResponse).toList())
                .build();
    }
}
