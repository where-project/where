package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateCommentRequest;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Comment;
import com.where.where.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final ModelMapperService modelMapperService;

	public CreateCommentRequest add(CreateCommentRequest createCommentDto) {
		Comment comment = this.modelMapperService.forRequest().map(createCommentDto, Comment.class);
		commentRepository.save(comment);
		return createCommentDto;
	}

	public List<CommentDto> getAll() {
		List<Comment> result = commentRepository.findAll();
		List<CommentDto> response = result.stream()
				.map(comment -> modelMapperService.forDto().map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public CommentDto getById(Long id) {
		Comment comment = commentRepository.getById(id);
		CommentDto response = modelMapperService.forDto().map(comment, CommentDto.class);
		return response;
	}

	public void delete(Long id) {
		commentRepository.deleteById(id);
	}

	public CommentDto update(CommentDto updateCommentDto) {
		Comment comment = modelMapperService.forRequest().map(updateCommentDto, Comment.class);
		commentRepository.save(comment);
		return updateCommentDto;
	}

	public List<CommentDto> getByPlaceId(Long id) {
		List<Comment> result = commentRepository.getByPlaceId(id);
		List<CommentDto> response = result.stream()
				.map(comment -> modelMapperService.forDto().map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return response;
	}

}
