package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessHourDto {
    private Long id;
    private String day;
    private String startTime;
    private String closingTime;
    private String status;

    @NotNull(message = "Must be not null")
    @Positive
    private Long placeId;
}
