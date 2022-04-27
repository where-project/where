package com.where.where.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.where.where.dto.CreatePlaceRequest;
import com.where.where.dto.PlaceDto;
import com.where.where.dto.UpdatePlaceRequest;
import com.where.where.service.PlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceController {
	private final PlaceService placeService;

	@GetMapping("/getAll")
	public ResponseEntity<List<PlaceDto>> getAll() {
		return new ResponseEntity<List<PlaceDto>>(placeService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CreatePlaceRequest> add(@Valid @RequestBody CreatePlaceRequest createPlaceRequest) {
		return new ResponseEntity<CreatePlaceRequest>(placeService.add(createPlaceRequest), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<PlaceDto> getById(@Valid @PathVariable Long id) {
		return new ResponseEntity<PlaceDto>(placeService.getById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		placeService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UpdatePlaceRequest> update(@Valid @PathVariable Long id,
			@Valid @RequestBody UpdatePlaceRequest updatePlaceDto) {
		return new ResponseEntity<UpdatePlaceRequest>(placeService.update(id, updatePlaceDto), HttpStatus.OK);
	}
}
