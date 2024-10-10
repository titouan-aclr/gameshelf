package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Location;
import com.titouanaclr.gameshelf.model.LocationRequest;
import com.titouanaclr.gameshelf.model.User;
import com.titouanaclr.gameshelf.repository.LocationRepository;
import com.titouanaclr.gameshelf.security.UserPrincipal;
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
}
