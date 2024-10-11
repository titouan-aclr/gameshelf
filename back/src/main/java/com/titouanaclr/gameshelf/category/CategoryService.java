package com.titouanaclr.gameshelf.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Iterable<CategoryResponse> findAll() {
        Iterable<Category> categories = this.categoryRepository.findAll();
        return StreamSupport.stream(categories.spliterator(), false)
                .map(categoryMapper::toCategoryResponse) // Utilisation du mapper
                .collect(Collectors.toList());
    }

    public Integer create(CategoryCreateRequest request) {
        Category category = categoryMapper.toCategory(request);
        return this.categoryRepository.save(category).getId();
    }

    public CategoryResponse update(CategoryUpdateRequest request) {
        Category category = this.categoryRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID " + request.id()));

        category.setName(request.name());
        category.setDescription(request.description());

        return categoryMapper.toCategoryResponse(this.categoryRepository.save(category));
    }
}
