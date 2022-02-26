package com.where.where.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.CategoryDto;
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
    public CategoryDto add(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @GetMapping("/getById/{id}")
    public CategoryDto getById(@Valid @RequestParam Long id) {
        return categoryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/update")
    public CategoryDto update(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }
}
