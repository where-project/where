package com.where.where.service;

import com.where.where.dto.CategoryDto;
import com.where.where.exception.CategoryNameAlreadyExistsException;
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

	public CategoryDto add(CategoryDto categoryDto) {
		checkIfCategoryExists(categoryDto.getCategoryName());
		Category category = this.modelMapperService.forRequest().map(categoryDto, Category.class);
		categoryRepository.save(category);
		return categoryDto;
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
		Category category = categoryRepository.getById(id);
		CategoryDto response = modelMapperService.forDto().map(category, CategoryDto.class);
		return response;
	}

	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public CategoryDto update(CategoryDto updateCategoryDto) {
		Category category = modelMapperService.forRequest().map(updateCategoryDto, Category.class);
		checkIfCategoryExists(updateCategoryDto.getCategoryName());
		categoryRepository.save(category);
		return updateCategoryDto;
	}
	
}
