package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.model.Category;
import com.titouanaclr.gameshelf.model.CategoryRequest;
import com.titouanaclr.gameshelf.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Category update(CategoryRequest request) {
        if(request.id() == null) {
            throw new IllegalArgumentException("Category ID is needed to update it.");
        }

        Category category = this.categoryRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID " + request.id()));

        category.setName(request.name());
        category.setDescription(request.description());

        return this.categoryRepository.save(category);
    }
}
