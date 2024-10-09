package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Category;
import com.titouanaclr.gameshelf.model.CategoryRequest;
import com.titouanaclr.gameshelf.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Iterable<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Integer save(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        return this.categoryRepository.save(category).getId();
    }
}
