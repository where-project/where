package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateScoreRequest {

    @Min(value = 1, message = "Cannot be smaller than 1")
    @Max(value = 5, message = "Cannot be bigger than 5")
    private float venueScore;

    @Min(value = 1, message = "Cannot be smaller than 1")
    @Max(value = 5, message = "Cannot be bigger than 5")
    private float coronaScore;

    @NotNull(message = "Must be not null")
    private LocalDate createDate;

    @NotNull(message = "Must be not null")
    @Positive
    private Long userId;

    @NotNull(message = "Must be not null")
    @Positive
    private Long placeId;
}
