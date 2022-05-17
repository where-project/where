package com.where.where.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.CityDto;
import com.where.where.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {
	private final CityService cityService;

	@GetMapping("/getAll")
	public ResponseEntity<List<CityDto>> getAll() {
		return new ResponseEntity<List<CityDto>>(cityService.getAll(), HttpStatus.OK);
	}

}
