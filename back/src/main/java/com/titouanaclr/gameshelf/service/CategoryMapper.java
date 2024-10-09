package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }
}
