package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Register request object used to create a new user")
public class RegisterRequest {

    @Schema(description = "User's username", example = "userName93")
    @NotEmpty(message = "Username is mandatory")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Schema(description = "User's email", example = "user@example.com")
    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Schema(description = "User's password", example = "%dsJLs2xJsc3Gvgd$QhxAs", minLength = 8)
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min=8, message = "Password should be 8 characters long minimum")
    private String password;

}
