package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CityDto;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.City;
import com.where.where.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
	private final CityRepository cityRepository;
	private final ModelMapperService modelMapperService;

	public CityDto getById(Long id) {

		checkIfCityExists(id);

		City city = cityRepository.getById(id);

		return modelMapperService.forDto().map(city, CityDto.class);

	}

	public List<CityDto> getAll() {

		List<City> result = this.cityRepository.findAll();
		return result.stream().map(city -> this.modelMapperService.forDto().map(city, CityDto.class))
				.collect(Collectors.toList());
	}

	public void checkIfCityExists(Long id) {

		if (!cityRepository.existsById(id)) {
			throw new BusinessException("City does not found.");
		}
	}
}
