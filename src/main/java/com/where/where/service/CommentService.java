package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateCommentRequest;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Comment;
import com.where.where.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final ModelMapperService modelMapperService;

	public CreateCommentRequest add(CreateCommentRequest createCommentRequest) {
		Comment comment = this.modelMapperService.forRequest().map(createCommentRequest, Comment.class);
		commentRepository.save(comment);
		return createCommentRequest;
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

	public CreateCommentRequest update(Long id, CreateCommentRequest createCommentDto) {
		if (commentRepository.existsById(id)) {
			Comment comment = modelMapperService.forRequest().map(createCommentDto, Comment.class);
			comment.setId(id);
			commentRepository.save(comment);
			return createCommentDto;
		}
		throw new BusinessException("Comment does not found.");
	}

	public List<CommentDto> getByPlaceId(Long id) {
		List<Comment> result = commentRepository.getByPlaceId(id);
		List<CommentDto> response = result.stream()
				.map(comment -> modelMapperService.forDto().map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return response;
	}

}
