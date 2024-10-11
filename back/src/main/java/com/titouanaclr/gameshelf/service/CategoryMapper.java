package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category toCategory(CategoryUpdateRequest request) {
        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }

    public Category toCategory(CategoryCreateRequest request) {
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
