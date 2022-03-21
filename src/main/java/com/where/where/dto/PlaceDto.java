package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
	private Long id;
	private String placeName;
	private String country;
	private String city;
	private String address;
	private String workDays;
	private String WorkHours;
}
