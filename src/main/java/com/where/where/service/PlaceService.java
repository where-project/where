package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CreatePlaceRequest;
import com.where.where.dto.PlaceDto;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Place;
import com.where.where.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;
	private final ModelMapperService modelMapperService;

	public CreatePlaceRequest add(CreatePlaceRequest createPlaceRequest) {
		Place place = this.modelMapperService.forRequest().map(createPlaceRequest, Place.class);
		placeRepository.save(place);
		return createPlaceRequest;
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

	public PlaceDto update(PlaceDto updatePlaceDto) {
		Place place = modelMapperService.forRequest().map(updatePlaceDto, Place.class);
		placeRepository.save(place);
		return updatePlaceDto;
	}
}
