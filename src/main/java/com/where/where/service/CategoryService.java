package com.where.where.service;

import com.where.where.dto.CategoryDto;
import com.where.where.dto.CreateCategoryRequest;
import com.where.where.dto.UpdateCategoryDto;
import com.where.where.exception.CategoryNameAlreadyExistsException;
import com.where.where.exception.CategoryNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Category;
import com.where.where.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapperService modelMapperService;

	public CreateCategoryRequest add(CreateCategoryRequest createCategoryDto) {
		checkIfCategoryExists(createCategoryDto.getCategoryName());
		Category category = this.modelMapperService.forRequest().map(createCategoryDto, Category.class);
		categoryRepository.save(category);
		return createCategoryDto;
	}

	private void checkIfCategoryExists(String categoryName) {
		if (categoryRepository.existsByCategoryName(categoryName)) {
			throw new CategoryNameAlreadyExistsException("Category already exists");
		}
	}

	public List<CategoryDto> getAll() {
		List<Category> result = categoryRepository.findAll();
		List<CategoryDto> response = result.stream()
				.map(category -> modelMapperService.forDto().map(category, CategoryDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public CategoryDto getById(Long id) {
		checkIfCategoryExistsById(id);
		Category category = categoryRepository.getById(id);
		CategoryDto response = modelMapperService.forDto().map(category, CategoryDto.class);
		return response;
	}

	public void delete(Long id) {
		checkIfCategoryExistsById(id);
		categoryRepository.deleteById(id);
	}

	public UpdateCategoryDto update(Long id, UpdateCategoryDto updateCategoryDto) {
		checkIfCategoryExistsById(id);
		Category category = modelMapperService.forRequest().map(updateCategoryDto, Category.class);
		checkIfCategoryExists(updateCategoryDto.getCategoryName());
		category.setId(id);
		categoryRepository.save(category);
		return updateCategoryDto;
	}

	public void checkIfCategoryExistsById(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new CategoryNotFoundException("Category not found.");
		}
	}

}
