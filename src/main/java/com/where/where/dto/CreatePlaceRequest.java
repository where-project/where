package com.where.where.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaceRequest {
	@NotNull(message = "Must be not null")
	@Size(max = 20, min = 2, message = "Place Name must be between 2 and 20")
	private String placeName;

	@NotNull(message = "Must be not null")
	private String description;

	@NotNull(message = "Must be not null")
	private String phoneNumber;

	@NotNull(message = "Must be not null")
	@Positive
	private Long ownerId;

	private List<CreatePlaceCategoryRequest> createPlaceCategoryRequests;
}
