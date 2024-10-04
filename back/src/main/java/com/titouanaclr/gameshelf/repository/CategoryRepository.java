package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
