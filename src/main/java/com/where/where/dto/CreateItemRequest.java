package com.where.where.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequest {

	@NotNull
	private String title;
	@NotNull

	private String description;

	@NotNull
	@Min(value = 0L)
	private Double price;

}
