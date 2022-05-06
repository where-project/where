package com.where.where.dto.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.where.where.dto.CreateItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {

	@Positive
	@NotNull
	private Long placeId;
	
	@Positive
	@NotNull
	private Long menuTypeId;
	private List<CreateItemRequest> createItemRequest;
}
