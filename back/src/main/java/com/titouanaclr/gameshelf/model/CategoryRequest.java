package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Category request object used to create or update a category")
public record CategoryRequest(
        @Schema(description = "The unique identifier of the category", example = "1", nullable = true)
        Integer id,
        @Schema(description = "The name of the category", example = "Action")
        @NotNull(message = "200")
        @NotEmpty(message = "200")
        String name,
        @Schema(description = "The description of the category", example = "Games that focus on intense gameplay")
        @NotNull(message = "201")
        @NotEmpty(message = "201")
        @Size(max = 1000, message = "202")
        String description
) { }
