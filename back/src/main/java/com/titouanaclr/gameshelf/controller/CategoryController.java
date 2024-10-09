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
}
