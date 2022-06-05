package com.where.where.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.BusinessHourDto;
import com.where.where.dto.CreateBusinessHourRequest;
import com.where.where.service.BusinessHourService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/business-hours")
@RequiredArgsConstructor
public class BusinessHourController {
	private final BusinessHourService businessHourService;

	@PostMapping("/add")
	public ResponseEntity<List<CreateBusinessHourRequest>> add(
			@RequestBody List<CreateBusinessHourRequest> businessHour) {
		return new ResponseEntity<List<CreateBusinessHourRequest>>(businessHourService.add(businessHour),
				HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<BusinessHourDto>> getAll() {
		return new ResponseEntity<List<BusinessHourDto>>(businessHourService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<BusinessHourDto> getById(@Valid @PathVariable Long id) {
		return new ResponseEntity<BusinessHourDto>(businessHourService.getById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		businessHourService.delete(id);
	}

}
