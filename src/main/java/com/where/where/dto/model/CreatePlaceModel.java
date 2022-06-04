package com.where.where.dto.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.where.where.dto.CreateItemRequest;
import com.where.where.dto.CreateLocationRequest;
import com.where.where.dto.CreatePlaceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaceModel {

	@NotNull
	CreatePlaceRequest createPlaceRequest;
	@NotNull
	CreateLocationRequest createLocationRequest;
	@NotNull
	List<CreateItemRequest> createItemRequest;
}
