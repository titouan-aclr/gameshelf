package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.exception.UnauthorizedActionException;
import com.titouanaclr.gameshelf.model.Location;
import com.titouanaclr.gameshelf.model.LocationRequest;
import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.repository.LocationRepository;
import com.titouanaclr.gameshelf.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final UserService userService;
    private final LocationMapper locationMapper;

    public Iterable<Location> findAllCurrentUserLocations(Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        return this.locationRepository.findByUserId(userPrincipal.getUserId());
    }

    public Integer saveCurrentUserLocation(LocationRequest request, Authentication currentUser) {
        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        if(request.user() == null) {
            User user = this.userService.findById(userPrincipal.getUserId());
            request = request.withUser(user);
        }
        Location location = locationMapper.toLocation(request);
        return this.locationRepository.save(location).getId();
    }

    public Location update(LocationRequest request, Authentication currentUser) {
        Location location = this.locationRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID " + request.id()));

        UserPrincipal userPrincipal = ((UserPrincipal) currentUser.getPrincipal());
        if(userPrincipal.getUserId() != location.getUser().getId()) {
            throw new UnauthorizedActionException("Location with ID " + request.id() + " does not belong to current user");
        }

        location.setName(request.name());
        location.setDescription(request.description());
        return this.locationRepository.save(location);
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
