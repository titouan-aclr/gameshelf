package com.titouanaclr.gameshelf.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        Integer id,
        @NotNull(message = "200")
        @NotEmpty(message = "200")
        String name,
        @NotNull(message = "201")
        @NotEmpty(message = "201")
        @Size(max = 1000)
        String description
) { }
