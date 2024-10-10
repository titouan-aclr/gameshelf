package com.titouanaclr.gameshelf.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LocationRequest(
        Integer id,
        @NotNull(message = "400")
        @NotEmpty(message = "400")
        String name,
        @NotNull(message = "401")
        @NotEmpty(message = "401")
        String description,
        User user
) {
    public LocationRequest withUser(User user) {
        return new LocationRequest(id(), name(), description(), user);
    }
}
