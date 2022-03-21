package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CreatePlaceRequest;
import com.where.where.dto.PlaceDto;
import com.where.where.dto.UpdatePlaceRequest;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Place;
import com.where.where.model.PlaceCategory;
import com.where.where.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;
	private final ModelMapperService modelMapperService;
	private final PlaceCategoryService placeCategoryService;

	public CreatePlaceRequest add(CreatePlaceRequest createPlaceRequest) {
		Place place = this.modelMapperService.forRequest().map(createPlaceRequest, Place.class);
		List<PlaceCategory> placeCategories = createPlaceRequest.getCreatePlaceCategoryRequests().stream()
				.map(placeCategory -> modelMapperService.forRequest().map(placeCategory, PlaceCategory.class))
				.collect(Collectors.toList());
		place.setPlaceCategories(mappingPlaceCategory(placeCategories, place));
		placeRepository.save(place);
		return createPlaceRequest;
	}

	private List<PlaceCategory> mappingPlaceCategory(List<PlaceCategory> placeCategories, Place place) {
		if (!placeCategories.isEmpty()) {
			for (PlaceCategory placeCategory : placeCategories) {
				placeCategory.setId(null);
				placeCategory.setPlace(place);
			}
			return placeCategories;
		}
		throw new BusinessException("Category id is not specified.");
	}

	public List<PlaceDto> getAll() {
		List<Place> result = placeRepository.findAll();
		List<PlaceDto> response = result.stream().map(place -> modelMapperService.forDto().map(place, PlaceDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public PlaceDto getById(Long id) {
		Place place = placeRepository.getById(id);
		PlaceDto response = modelMapperService.forDto().map(place, PlaceDto.class);
		return response;
	}

	public void delete(Long id) {
		placeRepository.deleteById(id);
	}

	public UpdatePlaceRequest update(UpdatePlaceRequest updatePlaceDto) {
		Place place = modelMapperService.forRequest().map(updatePlaceDto, Place.class);
		placeCategoryService.deleteByPlaceId(updatePlaceDto.getId());
		List<PlaceCategory> placeCategories = updatePlaceDto.getCreatePlaceCategoryRequests().stream()
				.map(placeCategory -> modelMapperService.forRequest().map(placeCategory, PlaceCategory.class))
				.collect(Collectors.toList());
		place.setPlaceCategories(mappingPlaceCategory(placeCategories, place));
		placeRepository.save(place);
		return updatePlaceDto;
	}
}
