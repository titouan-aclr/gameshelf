package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.*;
import org.springframework.stereotype.Service;

@Service
public class LocationMapper {

    public Location toLocation(LocationCreateRequest request, User user) {
        return Location.builder()
                .name(request.name())
                .description(request.description())
                .user(user)
                .build();
    }

    public LocationResponse toLocationResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .description(location.getDescription())
                .createdAt(location.getCreatedAt())
                .build();
    }
}
