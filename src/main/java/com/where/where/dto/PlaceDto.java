package com.where.where.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
	private Long id;
	private String placeName;
	private String description;
	private String phoneNumber;
	private LocalDate creationDate;
	private Long locationId;
	private String locationCountry;
	private String locationCityName;
	private String locationAddress;
	private List<PlaceCategoryDto> placeCategories;
	private Long ownerId;
	private String ownerUsername;
	private boolean isActive;
	private boolean status;
}
