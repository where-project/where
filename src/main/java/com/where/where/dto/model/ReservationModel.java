package com.where.where.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationModel {
	private String email;
	private String reservationDate;
	private String reservationTime;
	private String placeName;
}
