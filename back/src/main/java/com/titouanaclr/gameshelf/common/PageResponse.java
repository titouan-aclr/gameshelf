package com.titouanaclr.gameshelf.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Paged response with metadata about the current page and the content.")
public class PageResponse<T> {

    @Schema(description = "List of items for the current page")
    private List<T> content;
    @Schema(description = "Current page number (zero-based)", example = "0")
    private int pageNumber;
    @Schema(description = "Number of items per page", example = "10")
    private int pageSize;
    @Schema(description = "Total number of elements across all pages", example = "100")
    private long totalElements;
    @Schema(description = "Total number of pages available", example = "4")
    private int totalPages;
    @Schema(description = "Whether this is the first page", example = "true")
    private boolean first;
    @Schema(description = "Whether this is the last page", example = "false")
    private boolean last;
}
