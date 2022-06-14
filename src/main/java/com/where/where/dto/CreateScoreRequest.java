package com.where.where.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateScoreRequest {

	@Min(value = 0, message = "Cannot be smaller than 0")
	@Max(value = 5, message = "Cannot be bigger than 5")
	@Positive
	private float venueScore;

	@Min(value = 0, message = "Cannot be smaller than 0")
	@Max(value = 5, message = "Cannot be bigger than 5")
	@Positive
	private float coronaScore;

	@NotNull(message = "Must be not null")
	@Positive
	private Long userId;

	@NotNull(message = "Must be not null")
	@Positive
	private Long placeId;
}
