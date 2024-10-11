package com.titouanaclr.gameshelf.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response object representing a game category")
public class CategoryResponse {

    @Schema(description = "Unique identifier of the category", example = "1")
    private Integer id;

    @Schema(description = "Name of the category", example = "Action")
    private String name;

    @Schema(description = "Description of the category", example = "Games that focus on intense gameplay")
    private String description;

}
