package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBusinessHourRequest {
    private String day;
    private String status;
    private String hour;

    @NotNull(message = "Must be not null")
    @Positive
    private Long placeId;
}
