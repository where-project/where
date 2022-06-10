package com.where.where.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {

	@NotNull(message = "Must be not null")
	@Size(max = 20, min = 2, message = "Must be between 2 and 20")
	private String commentText;

	@NotNull(message = "Must be not null")
	@Positive
	private Long userId;

	@NotNull(message = "Must be not null")
	@Positive
	private Long placeId;
}
