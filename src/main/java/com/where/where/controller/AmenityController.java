package com.where.where.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.AmenityDto;
import com.where.where.service.AmenityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/amenities")
@RequiredArgsConstructor
public class AmenityController {
	private final AmenityService amenityService;

	@GetMapping("/getAll")
	public ResponseEntity<List<AmenityDto>> getAll() {
		return new ResponseEntity<List<AmenityDto>>(amenityService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<AmenityDto> add(@Valid @RequestBody AmenityDto amenityDto) {
		return new ResponseEntity<AmenityDto>(amenityService.add(amenityDto), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<AmenityDto> getById(@Valid @PathVariable Long id) {
		return new ResponseEntity<AmenityDto>(amenityService.getById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		amenityService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AmenityDto> update(@Valid @PathVariable Long id, @Valid @RequestBody AmenityDto amenityDto) {
		return new ResponseEntity<AmenityDto>(amenityService.update(id, amenityDto), HttpStatus.OK);
	}
}
