package com.where.where.dto.model;

import javax.validation.constraints.NotNull;

import com.where.where.dto.CreateCommentRequest;
import com.where.where.dto.CreateScoreRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewModel {
	@NotNull
	private CreateCommentRequest createCommentRequest;
	@NotNull
	private CreateScoreRequest createScoreRequest;
}
