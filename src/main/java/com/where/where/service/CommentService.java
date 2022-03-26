package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateCommentRequest;
import com.where.where.exception.CommentNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Comment;
import com.where.where.repository.CommentRepository;
import com.where.where.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentService {
	private final CommentRepository commentRepository;
	private final ModelMapperService modelMapperService;
	@Lazy
	private final PlaceService placeService;
	private final UserService userService;

	public CreateCommentRequest add(CreateCommentRequest createCommentRequest) {
		checkIfPlaceExists(createCommentRequest.getPlaceId());
		checkIfUserExists(createCommentRequest.getUserId());
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
		checkIfCommentExists(id);
		Comment comment = commentRepository.getById(id);
		CommentDto response = modelMapperService.forDto().map(comment, CommentDto.class);
		return response;
	}

	public void delete(Long id) {
		checkIfCommentExists(id);
		commentRepository.deleteById(id);
	}

	public CreateCommentRequest update(Long id, CreateCommentRequest createCommentRequest) {
		checkIfCommentExists(id);
		checkIfPlaceExists(createCommentRequest.getPlaceId());
		checkIfUserExists(createCommentRequest.getUserId());
		Comment comment = modelMapperService.forRequest().map(createCommentRequest, Comment.class);
		comment.setId(id);
		commentRepository.save(comment);
		return createCommentRequest;
	}

	public List<CommentDto> getByPlaceId(Long id) {
		checkIfPlaceExists(id);
		List<Comment> result = commentRepository.getByPlaceId(id);
		List<CommentDto> response = result.stream()
				.map(comment -> modelMapperService.forDto().map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return response;
	}

	private void checkIfPlaceExists(Long id) {
		placeService.checkIfPlaceExists(id);
	}

	private void checkIfUserExists(Long id) {
		userService.existsById(id);
	}

	private void checkIfCommentExists(Long id) {
		if (!commentRepository.existsById(id)) {
			throw new CommentNotFoundException("Comment not found");
		}
	}

}
