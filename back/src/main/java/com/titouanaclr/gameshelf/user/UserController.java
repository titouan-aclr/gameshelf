package com.titouanaclr.gameshelf.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "Manage users account and profile")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get user profile by ID", description = "Retrieve the profile of a user by their unique identifier. Profile retrieve only general information about the account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user profile", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("{user-id}")
    public ResponseEntity<UserProfileResponse> findUserProfileById(
            @Parameter(description = "The unique identifier of the user", example = "7")
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(this.userService.findUserProfileById(userId));
    }

    @Operation(summary = "Get current user account information", description = "Retrieve the account information of the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved current user profile", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAccountResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("current")
    public ResponseEntity<UserAccountResponse> findCurrentUser(Authentication currentUser) {
        return ResponseEntity.ok(this.userService.findCurrentUser(currentUser));
    }
}
