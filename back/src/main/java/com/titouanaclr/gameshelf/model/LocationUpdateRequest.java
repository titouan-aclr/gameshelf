package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Location update request object used to update an existing location")
public record LocationUpdateRequest(
        @Schema(description = "The unique identifier of the location", example = "63")
        @NotNull(message = "400")
        Integer id,
        @Schema(description = "The updated name of the location", example = "Living Room")
        @NotNull(message = "401")
        @NotEmpty(message = "401")
        String name,
        @Schema(description = "The updated description of the location", example = "In the living room of our house, under the TV")
        @NotNull(message = "402")
        @NotEmpty(message = "402")
        String description
) { }
