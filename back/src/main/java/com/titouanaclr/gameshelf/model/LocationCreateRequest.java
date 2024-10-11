package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Location create request object used to add a new game location")
public record LocationCreateRequest(
        @Schema(description = "The name of the location", example = "Living Room")
        @NotNull(message = "401")
        @NotEmpty(message = "401")
        String name,
        @Schema(description = "A brief description of the location", example = "In the living room of our house, under the TV")
        @NotNull(message = "402")
        @NotEmpty(message = "402")
        String description
) { }
