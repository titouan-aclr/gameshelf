package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.exception.UnauthorizedActionException;
import com.titouanaclr.gameshelf.model.*;
import com.titouanaclr.gameshelf.repository.LocationRepository;
import com.titouanaclr.gameshelf.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final UserService userService;
    private final LocationMapper locationMapper;

    public Iterable<LocationResponse> findAllCurrentUserLocations(Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        Iterable<Location> locations = this.locationRepository.findByUserId(userPrincipal.getUserId());
        return StreamSupport.stream(locations.spliterator(), false)
                .map(locationMapper::toLocationResponse) // Utilisation du mapper
                .collect(Collectors.toList());
    }

    public Integer saveCurrentUserLocation(LocationCreateRequest request, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        User user = this.userService.findById(userPrincipal.getUserId());
        // TODO : verify that location with same name does not exists for user
        Location location = locationMapper.toLocation(request, user);
        return this.locationRepository.save(location).getId();
    }

    public LocationResponse update(LocationUpdateRequest request, Authentication currentUser) {
        Location location = this.locationRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID " + request.id()));

        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        if(userPrincipal.getUserId() != location.getUser().getId()) {
            throw new UnauthorizedActionException("Location with ID " + request.id() + " does not belong to current user");
        }

        location.setName(request.name());
        location.setDescription(request.description());
        return locationMapper.toLocationResponse(this.locationRepository.save(location));
    }

    public void delete(Integer locationId, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());

        Location location = this.locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID " + locationId));

        if (!location.getUser().getId().equals(userPrincipal.getUserId())) {
            throw new UnauthorizedActionException("Location with ID " + locationId + " does not belong to current user");
        }

        this.locationRepository.delete(location);
    }
}
