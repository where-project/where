package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "Must be not blank")
    private String createDate;
    private Long userId;
    private Long placeId;
}
