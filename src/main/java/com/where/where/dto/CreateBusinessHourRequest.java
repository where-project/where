package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBusinessHourRequest {
	private String day;
	private String status;
	private String startTime;
	private String closingTime;
}
