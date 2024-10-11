package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.Category;
import com.titouanaclr.gameshelf.model.CategoryRequest;
import com.titouanaclr.gameshelf.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Categories", description = "Manage game categories")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Retrieve a list of all game categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("categories")
    public ResponseEntity<Iterable<Category>> findAll() {
        return ResponseEntity.ok(this.categoryService.findAll());
    }

    @Operation(summary = "Create a new category", description = "Create a new game category. Only accessible by admin users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category successfully created", content = @Content(mediaType = "application/json",schema = @Schema(type = "integer", example = "1"))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("admin/categories")
    public ResponseEntity<Integer> saveCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(this.categoryService.save(request));
    }

    @Operation(summary = "Update an existing category",description = "Update an existing game category by its ID. Only accessible by admin users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category successfully updated", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input data or ID mismatch", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("admin/categories/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @RequestBody @Valid CategoryRequest request
    ) {

        if (!id.equals(request.id())) {
            throw new IllegalArgumentException("ID from URL must be the same as ID from body");
        }

        return ResponseEntity.ok(this.categoryService.update(request));
    }
}
