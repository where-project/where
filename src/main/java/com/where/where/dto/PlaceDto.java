package com.where.where.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
	private Long id;
	private String workDays;
	private String WorkHours;
	private String placeName;
	private Long locationId;
	private String locationCountry;
	private String locationCity;
	private String locationAddress;
	private List<PlaceCategoryDto> placeCategories;
	private Long ownerId;
	private String ownerUsername;

}
