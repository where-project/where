package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.where.where.dto.CreatePlaceCategoryRequest;
import com.where.where.dto.PlaceCategoryDto;
import com.where.where.exception.PlaceCategoryNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.PlaceCategory;
import com.where.where.repository.PlaceCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlaceCategoryService {
	private final PlaceCategoryRepository placeCategoryRepository;
	private final ModelMapperService modelMapperService;
	@Lazy
	private final PlaceService placeService;

	public CreatePlaceCategoryRequest add(CreatePlaceCategoryRequest createPlaceCategoryRequest) {
		PlaceCategory score = modelMapperService.forRequest().map(createPlaceCategoryRequest, PlaceCategory.class);
		placeCategoryRepository.save(score);
		return createPlaceCategoryRequest;
	}

	public List<PlaceCategoryDto> getAll() {
		List<PlaceCategory> result = placeCategoryRepository.findAll();
		List<PlaceCategoryDto> response = result.stream()
				.map(placeCategory -> modelMapperService.forDto().map(placeCategory, PlaceCategoryDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public PlaceCategoryDto getById(Long id) {
		checkIfPlaceCategoryExists(id);
		PlaceCategory placeCategory = placeCategoryRepository.getById(id);
		return modelMapperService.forDto().map(placeCategory, PlaceCategoryDto.class);
	}

	public void delete(Long id) {
		checkIfPlaceCategoryExists(id);
		placeCategoryRepository.deleteById(id);
	}

	public CreatePlaceCategoryRequest update(Long id, CreatePlaceCategoryRequest createPlaceCategoryRequest) {
		checkIfPlaceCategoryExists(id);
		PlaceCategory placeCategory = modelMapperService.forRequest().map(createPlaceCategoryRequest,
				PlaceCategory.class);
		placeCategory.setId(id);
		placeCategoryRepository.save(placeCategory);
		return createPlaceCategoryRequest;
	}

	public boolean deleteByPlaceId(Long id) {
		checkIfPlaceExists(id);
		if (!placeCategoryRepository.findByPlace_Id(id).isEmpty()) {
			placeCategoryRepository.deleteAll(placeCategoryRepository.findByPlace_Id(id));
		}
		return true;
	}

	private void checkIfPlaceCategoryExists(Long id) {
		if (!placeCategoryRepository.existsById(id)) {
			throw new PlaceCategoryNotFoundException("Place category not found.");
		}
	}

	private void checkIfPlaceExists(Long id) {
		placeService.checkIfPlaceExists(id);
	}

}
