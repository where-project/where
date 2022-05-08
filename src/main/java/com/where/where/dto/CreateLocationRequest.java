package com.where.where.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationRequest {
	@NotNull(message = "Must be not null")
	@Positive
	private Long cityId;

	@NotNull(message = "Must be not null")
	@Positive
	private double lat;

	@NotNull(message = "Must be not null")
	@Positive
	private double lng;

	@NotBlank(message = "Must be not blank")
	private String country;

	@NotBlank(message = "Must be not blank")
	private String address;
}
