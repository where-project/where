package com.where.where.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.where.where.dto.CreateScoreRequest;
import com.where.where.dto.ScoreDto;
import com.where.where.service.ScoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor
public class ScoreController {
	private final ScoreService scoreService;

	@GetMapping("/getAll")
	public ResponseEntity<List<ScoreDto>> getAll() {
		return new ResponseEntity<List<ScoreDto>>(scoreService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CreateScoreRequest> add(@Valid @RequestBody CreateScoreRequest createScoreRequest) {
		return new ResponseEntity<CreateScoreRequest>(scoreService.add(createScoreRequest), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ScoreDto> getById(@Valid @PathVariable Long id) {
		return new ResponseEntity<ScoreDto>(scoreService.getById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		scoreService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CreateScoreRequest> update(@Valid @PathVariable Long id,
			@Valid @RequestBody CreateScoreRequest createScoreRequest) {
		return new ResponseEntity<CreateScoreRequest>(scoreService.update(id, createScoreRequest), HttpStatus.OK);
	}
}
