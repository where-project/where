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

import com.where.where.dto.CreatePlaceRequest;
import com.where.where.dto.PlaceDto;
import com.where.where.dto.UpdatePlaceRequest;
import com.where.where.dto.model.CreatePlaceModel;
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

	@GetMapping("/getById/{id}")
	public ResponseEntity<PlaceDto> getById(@Valid @PathVariable Long id) {
		return new ResponseEntity<PlaceDto>(placeService.getById(id), HttpStatus.OK);
	}

	@GetMapping("/checkIsActive/")
	public ResponseEntity<Boolean> checkIsActive(@RequestParam Long id) {
		return new ResponseEntity<>(placeService.checkIsActive(id), HttpStatus.OK);
	}

	@GetMapping("/checkStatus/")
	public ResponseEntity<Boolean> checkIsStatus(@RequestParam Long id) {
		return new ResponseEntity<>(placeService.checkIsStatus(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CreatePlaceRequest> add(@Valid @RequestBody CreatePlaceModel createPlaceModel) {
		return new ResponseEntity<CreatePlaceRequest>(placeService.add(createPlaceModel), HttpStatus.OK);
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

	@PutMapping("/changeActive/{id}")
	public ResponseEntity<Boolean> changeActive(@PathVariable Long id) {
		return new ResponseEntity<>(placeService.changeActive(id), HttpStatus.OK);
	}

	@PutMapping("/changeStatus/{id}")
	public ResponseEntity<Boolean> changeStatus(@PathVariable Long id) {
		return new ResponseEntity<>(placeService.changeStatus(id), HttpStatus.OK);
	}

	@GetMapping("/filterByCityId/{id}")
	public ResponseEntity<List<PlaceDto>> filterByCityId(@Valid @PathVariable Long id) {
		return new ResponseEntity<List<PlaceDto>>(placeService.filterByCityId(id), HttpStatus.OK);
	}

	@GetMapping("/filterByCategoryId/{id}")
	public ResponseEntity<List<PlaceDto>> filterByCategoryId(@PathVariable Long id) {
		return new ResponseEntity<>(placeService.filterByCategoryId(id), HttpStatus.OK);
	}

	@GetMapping("/filterByCityIdAndCategoryId/{cityId}/{categoryId}")
	public ResponseEntity<List<PlaceDto>> filterByCityIdAndCategoryId(@PathVariable Long cityId,
			@PathVariable Long categoryId) {
		return new ResponseEntity<>(placeService.filterByCityIdAndCategoryId(cityId, categoryId), HttpStatus.OK);
	}
}
