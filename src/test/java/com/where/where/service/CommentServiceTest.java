package com.where.where.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;

import com.where.where.TestSupport;
import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateCommentRequest;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Comment;
import com.where.where.repository.CommentRepository;

class CommentServiceTest extends TestSupport {
	private CommentRepository commentRepository;
	private CommentService commentService;
	private ModelMapperService modelMapperService;
	private ModelMapper modelMapper;

	@BeforeEach
	void setUp() {
		commentRepository = mock(CommentRepository.class);
		modelMapperService = mock(ModelMapperService.class);
		modelMapper = mock(ModelMapper.class);
		commentService = new CommentService(commentRepository, modelMapperService);
	}

	@Test
	void whenCreateCommentCalledWithValidRequest_itShouldReturnValidCreateCommentRequest() {
		CreateCommentRequest createCommentRequest = generateCreateCommentRequest();
		Comment comment = generateComment(createCommentRequest);

		// Determine mock services behavior regarding test scenario
		when(modelMapperService.forRequest()).thenReturn(modelMapper);
		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(comment);
		when(commentRepository.save(ArgumentMatchers.any(Comment.class))).thenReturn(comment);

		CreateCommentRequest result = commentService.add(createCommentRequest);

		// Check results and verify the mock methods are called
		assertEquals(result, createCommentRequest);
		assertThat(result.getCommentText()).isNotNull().isEqualTo(createCommentRequest.getCommentText());
		assertThat(result.getCreateDate()).isNotNull().isEqualTo(createCommentRequest.getCreateDate());
		assertThat(result.getPlaceId()).isNotNull().isEqualTo(createCommentRequest.getPlaceId());
		assertThat(result.getUserId()).isNotNull().isEqualTo(createCommentRequest.getUserId());

		assertThat(result.getCreateDate()).isEqualTo(createCommentRequest.getCreateDate());
		verify(commentRepository).save(comment);
	}

	@Test
	void whenCommentIdCalled_itShouldReturnCommentDto() {
		// Generate comment and commentDto
		Comment comment = generateComment();
		CommentDto commentDto = generateCommentDto(comment);

		// Determine mock services behavior regarding test scenario
		when(modelMapperService.forDto()).thenReturn(modelMapper);
		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(commentDto);
		when(commentRepository.getById(1L)).thenReturn(comment);

		// Call the testing method
		Comment result = commentRepository.getById(1L);

		// Check results and verify the mock methods are called
		assertEquals(result.getId(), commentDto.getId());
		assertEquals(result.getUser().getFirstName(), commentDto.getFirstName());

		assertThat(result.getCommentText()).isNotNull().isEqualTo(commentDto.getCommentText());
		assertThat(result.getUser().getFirstName()).isNotNull().isEqualTo(commentDto.getFirstName());
		assertThat(result.getUser().getLastName()).isNotNull().isEqualTo(commentDto.getLastName());
		assertThat(result.getPlace().getPlaceName()).isNotNull().isEqualTo(commentDto.getPlaceName());
		assertThat(result.getCreateDate()).isNotNull().isEqualTo(commentDto.getCreateDate());

		assertThat(result.getCommentText()).isNotBlank().isEqualTo(commentDto.getCommentText());
		assertThat(result.getUser().getFirstName()).isNotBlank().isEqualTo(commentDto.getFirstName());
		assertThat(result.getUser().getLastName()).isNotBlank().isEqualTo(commentDto.getLastName());
		assertThat(result.getPlace().getPlaceName()).isNotBlank().isEqualTo(commentDto.getPlaceName());
		assertThat(result.getCreateDate()).isEqualTo(commentDto.getCreateDate());
		verify(commentRepository).getById(1L);
	}
}