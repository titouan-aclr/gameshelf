package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response object containing user account information.")
public class UserAccountResponse {

    @Schema(description = "The unique identifier of the user.", example = "85")
    private Integer id;

    @Schema(description = "The username of the user.", example = "john_doe")
    private String username;

    @Schema(description = "The email of the user.", example = "user@example.com")
    private String email;

    @Schema(description = "The date and time when the user account was created.", example = "2023-02-06T10:00:00Z")
    private Date createdAt;

    @Schema(description = "The set of roles assigned to the user.")
    private Set<Role> roles;

    @Schema(description = "The various game locations of the user.")
    private List<LocationResponse> locations;
}