package com.where.where.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaceRequest {
	private String placeName;
	private Long locationId;
	private Long ownerId;
}
