package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateScoreRequest {
	private float venueScore;
	private float coronaScore;
	private String createDate;
	private Long userId;
	private Long placeId;
}
