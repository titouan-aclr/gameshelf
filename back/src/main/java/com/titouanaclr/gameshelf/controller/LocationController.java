package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.Location;
import com.titouanaclr.gameshelf.model.LocationRequest;
import com.titouanaclr.gameshelf.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("users/current/locations/{location-id}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable("location-id") Integer locationId,
            @RequestBody @Valid LocationRequest request,
            Authentication currentUser
    ) {
        if(request.id() == null) {
            throw new IllegalArgumentException("Location ID is needed to update it.");
        }

        if (!locationId.equals(request.id())) {
            throw new IllegalArgumentException("ID from URL must be the same as ID from body");
        }

        return ResponseEntity.ok(this.locationService.update(request, currentUser));
    }
}
