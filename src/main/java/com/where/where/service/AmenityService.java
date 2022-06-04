package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.AmenityDto;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Amenity;
import com.where.where.repository.AmenityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmenityService {

	private final AmenityRepository amenityRepository;
	private final ModelMapperService modelMapperService;

	public AmenityDto add(AmenityDto amenityDto) {
		Amenity amenity = this.modelMapperService.forRequest().map(amenityDto, Amenity.class);
		amenityRepository.save(amenity);
		return amenityDto;
	}

	public List<AmenityDto> getAll() {
		List<Amenity> result = amenityRepository.findAll();
		List<AmenityDto> response = result.stream()
				.map(category -> modelMapperService.forDto().map(category, AmenityDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public AmenityDto getById(Long id) {
		checkIfAmenityExistsById(id);
		Amenity amenity = amenityRepository.getById(id);
		AmenityDto response = modelMapperService.forDto().map(amenity, AmenityDto.class);
		return response;
	}

	public void delete(Long id) {
		checkIfAmenityExistsById(id);
		amenityRepository.deleteById(id);
	}

	public AmenityDto update(Long id, AmenityDto amenityDto) {
		checkIfAmenityExistsById(id);
		Amenity amenity = modelMapperService.forRequest().map(amenityDto, Amenity.class);

		amenity.setId(id);
		amenityRepository.save(amenity);
		return amenityDto;
	}

	public void checkIfAmenityExistsById(Long id) {
		if (!amenityRepository.existsById(id)) {
			throw new BusinessException("Amenity not found.");
		}
	}

}
