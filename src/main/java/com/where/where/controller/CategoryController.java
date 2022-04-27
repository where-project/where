package com.where.where.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.where.where.dto.CategoryDto;
import com.where.where.dto.CreateCategoryRequest;
import com.where.where.dto.UpdateCategoryDto;
import com.where.where.service.CategoryService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAll() {
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CreateCategoryRequest> add(@Valid @RequestBody CreateCategoryRequest categoryDto) {
		return new ResponseEntity<CreateCategoryRequest>(categoryService.add(categoryDto), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<CategoryDto> getById(@Valid @PathVariable Long id) {
		return new ResponseEntity<CategoryDto>(categoryService.getById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		categoryService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UpdateCategoryDto> update(@Valid @PathVariable Long id,
			@Valid @RequestBody UpdateCategoryDto updateCategoryDto) {
		return new ResponseEntity<UpdateCategoryDto>(categoryService.update(id, updateCategoryDto), HttpStatus.OK);
	}
}
