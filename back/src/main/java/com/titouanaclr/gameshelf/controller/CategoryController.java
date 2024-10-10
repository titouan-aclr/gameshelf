package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.Category;
import com.titouanaclr.gameshelf.model.CategoryRequest;
import com.titouanaclr.gameshelf.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("categories")
    public ResponseEntity<Iterable<Category>> findAll() {
        return ResponseEntity.ok(this.categoryService.findAll());
    }

    @PostMapping("admin/categories")
    public ResponseEntity<Integer> saveCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(this.categoryService.save(request));
    }

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
