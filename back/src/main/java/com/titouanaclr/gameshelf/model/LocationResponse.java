package com.titouanaclr.gameshelf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Location response object representing a game location")
public class LocationResponse {
    @Schema(description = "The unique identifier of the location", example = "45")
    private Integer id;
    @Schema(description = "The name of the location", example = "Living Room")
    private String name;
    @Schema(description = "A brief description of the location", example = "In the living room of our house, under the TV")
    private String description;
    @Schema(description = "The timestamp of when the location was created", example = "2024-01-11T12:30:00Z")
    private Date createdAt;
}
