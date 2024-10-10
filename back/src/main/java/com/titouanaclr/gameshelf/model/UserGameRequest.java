package com.titouanaclr.gameshelf.model;

import jakarta.validation.constraints.NotNull;

public record UserGameRequest(
        int id,
        User user,
        @NotNull(message = "300")
        Game game,
        Location location
) {
    public UserGameRequest withUser(User user) {
        return new UserGameRequest(id(), user, game(), location());
    }
}
