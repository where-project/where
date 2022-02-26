package com.where.where.service;

import com.where.where.dto.CategoryDto;
import com.where.where.exception.CategoryNameAlreadyExistsException;
import com.where.where.model.Category;
import com.where.where.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto add(CategoryDto categoryDto) {
        if (categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
            throw new CategoryNameAlreadyExistsException("Category already exists");
        }
        Category category = new Category(categoryDto.getId(), categoryDto.getCategoryName());
        categoryRepository.save(category);
        return categoryDto;
    }
}
