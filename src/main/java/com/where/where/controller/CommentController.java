package com.where.where.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateCommentRequest;
import com.where.where.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping("/getAll")
	public ResponseEntity<List<CommentDto>> getAll() {
		return new ResponseEntity<List<CommentDto>>(commentService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CreateCommentRequest> add(@Valid @RequestBody CreateCommentRequest createCommentRequest) {
		return new ResponseEntity<CreateCommentRequest>(commentService.add(createCommentRequest), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<CommentDto> getById(@Valid @RequestParam Long id) {
		return new ResponseEntity<CommentDto>(commentService.getById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		commentService.delete(id);
	}

	@PutMapping("/update")
	public ResponseEntity<CommentDto> update(@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(commentService.update(commentDto), HttpStatus.OK);
	}

	@GetMapping("/getByPlaceId/{id}")
	public ResponseEntity<List<CommentDto>> getByPlaceId(@Valid @RequestParam Long id) {
		return new ResponseEntity<List<CommentDto>>(commentService.getByPlaceId(id), HttpStatus.OK);
	}
}
