package com.titouanaclr.gameshelf.user;

import com.titouanaclr.gameshelf.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response object containing user profile information.")
public class UserProfileResponse {

    @Schema(description = "The unique identifier of the user.", example = "85")
    private Integer id;

    @Schema(description = "The username of the user.", example = "john_doe")
    private String username;

    @Schema(description = "The date and time when the user account was created.", example = "2023-02-06T10:00:00Z")
    private Date createdAt;

    @Schema(description = "The set of roles assigned to the user.")
    private Set<Role> roles;
}