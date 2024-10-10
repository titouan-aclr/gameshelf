package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Location;
import com.titouanaclr.gameshelf.model.LocationRequest;
import org.springframework.stereotype.Service;

@Service
public class LocationMapper {

    public Location toLocation(LocationRequest request) {
        return Location.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .user(request.user())
                .build();
    }
}
