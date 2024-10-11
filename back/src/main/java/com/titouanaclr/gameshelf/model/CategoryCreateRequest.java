package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Category request object used to create a category")
public record CategoryCreateRequest(
        @Schema(description = "The name of the category", example = "Action")
        @NotNull(message = "201")
        @NotEmpty(message = "201")
        String name,
        @Schema(description = "The description of the category", example = "Games that focus on intense gameplay")
        @NotNull(message = "202")
        @NotEmpty(message = "202")
        @Size(max = 1000, message = "203")
        String description
) { }
