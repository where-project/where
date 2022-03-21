package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CreatePlaceCategoryRequest;
import com.where.where.dto.PlaceCategoryDto;
import com.where.where.exception.BusinessException;
import com.where.where.exception.ScoreNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.PlaceCategory;
import com.where.where.repository.PlaceCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceCategoryService {
	private final PlaceCategoryRepository placeCategoryRepository;
	private final ModelMapperService modelMapperService;

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
		PlaceCategory placeCategory = placeCategoryRepository.getById(id);
		return modelMapperService.forDto().map(placeCategory, PlaceCategoryDto.class);
	}

	public void delete(Long id) {
		placeCategoryRepository.deleteById(id);
	}

	public CreatePlaceCategoryRequest update(Long id, CreatePlaceCategoryRequest createPlaceCategoryRequest) {
		if (placeCategoryRepository.existsById(id)) {
			PlaceCategory placeCategory = modelMapperService.forRequest().map(createPlaceCategoryRequest,
					PlaceCategory.class);
			placeCategory.setId(id);
			placeCategoryRepository.save(placeCategory);
			return createPlaceCategoryRequest;
		}
		throw new ScoreNotFoundException("Place's category does not found.");

	}

	public boolean deleteByPlaceId(Long id) {
		if (!placeCategoryRepository.findByPlace_Id(id).isEmpty()) {
			placeCategoryRepository.deleteAll(placeCategoryRepository.findByPlace_Id(id));
		}
		return true;
	}

}
