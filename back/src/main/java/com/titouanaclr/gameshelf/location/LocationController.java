package com.titouanaclr.gameshelf.location;

import com.titouanaclr.gameshelf.exception.OperationNotPermittedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Locations", description = "Manage user's game locations")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping ("users/current/locations")
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "Get all locations for current user", description = "Retrieve a list of all locations associated to the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of locations", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Iterable<LocationResponse>> findAllCurrentUserLocations(Authentication currentUser) {
        return ResponseEntity.ok(this.locationService.findAllCurrentUserLocations(currentUser));
    }

    @Operation(summary = "Add a new location", description = "Create a new location for the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location created successfully and ID returned", content = @Content(mediaType = "application/json", schema = @Schema(type = "integer", example = "93"))),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid location data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Integer> addLocation(
            @RequestBody @Valid LocationCreateRequest request,
            Authentication currentUser
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.locationService.saveCurrentUserLocation(request, currentUser));
    }

    @Operation(summary = "Update an existing location", description = "Update the details of an existing location owned by the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid location ID or data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Location not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("{location-id}")
    public ResponseEntity<LocationResponse> updateLocation(
            @PathVariable("location-id") Integer locationId,
            @RequestBody @Valid LocationUpdateRequest request,
            Authentication currentUser
    ) {
        if(request.id() == null) {
            throw new IllegalArgumentException("Location ID is needed to update it.");
        }

        if (!locationId.equals(request.id())) {
            throw new OperationNotPermittedException("ID from URL must be the same as ID from body");
        }

        return ResponseEntity.ok(this.locationService.update(request, currentUser));
    }

    @Operation(summary = "Delete a location", description = "Delete an existing location owned by the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Location deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid location ID", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Location not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("{location-id}")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable("location-id") Integer locationId,
            Authentication currentUser
    ) {
        this.locationService.delete(locationId, currentUser);
        return ResponseEntity.noContent().build();
    }
}
