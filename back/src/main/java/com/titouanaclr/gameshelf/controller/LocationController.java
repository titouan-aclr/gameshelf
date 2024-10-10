package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.Location;
import com.titouanaclr.gameshelf.model.LocationRequest;
import com.titouanaclr.gameshelf.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("users/current/locations")
    public ResponseEntity<Iterable<Location>> findAllCurrentUserLocations(Authentication currentUser) {
        return ResponseEntity.ok(this.locationService.findAllCurrentUserLocations(currentUser));
    }

    @PostMapping("users/current/locations")
    public ResponseEntity<Integer> addLocation(
            @RequestBody @Valid LocationRequest request,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(this.locationService.saveCurrentUserLocation(request, currentUser));
    }
}
