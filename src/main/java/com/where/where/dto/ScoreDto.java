package com.where.where.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {

	private Long id;

	private float venueScore;

	private float coronaScore;

	private LocalDate createDate;

	private String firstName;

	private String lastName;

	private String placeName;

	private Long userId;

}
