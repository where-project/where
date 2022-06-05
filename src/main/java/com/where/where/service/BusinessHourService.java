package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.where.where.dto.BusinessHourDto;
import com.where.where.dto.CreateBusinessHourRequest;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.BusinessHour;
import com.where.where.repository.BusinessHourRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessHourService {
	private final BusinessHourRepository businessHourRepository;
	private final ModelMapperService modelMapperService;

	@Transactional
	public List<CreateBusinessHourRequest> add(List<CreateBusinessHourRequest> createBusinessHourRequest) {
		List<BusinessHour> businessHours = createBusinessHourRequest.stream()
				.map(item -> modelMapperService.forRequest().map(item, BusinessHour.class))
				.collect(Collectors.toList());
		businessHourRepository.saveAll(businessHours);
		return createBusinessHourRequest;
	}

	public List<BusinessHourDto> getAll() {
		List<BusinessHour> result = businessHourRepository.findAll();
		List<BusinessHourDto> response = result.stream()
				.map(business -> modelMapperService.forDto().map(business, BusinessHourDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public BusinessHourDto getById(Long id) {
		checkIfExistsById(id);
		BusinessHour businessHour = businessHourRepository.getById(id);
		BusinessHourDto response = modelMapperService.forDto().map(businessHour, BusinessHourDto.class);
		return response;
	}

	public void delete(Long id) {
		checkIfExistsById(id);
		businessHourRepository.deleteById(id);
	}

	private void checkIfExistsById(Long id) {
		if (businessHourRepository.existsById(id)) {
			throw new BusinessException("Business hour does not exists!");
		}
	}

}
