package com.where.where.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaceRequest {
    @NotNull(message = "Must be not null")
    @Size(max = 20, min = 2, message = "Place Name must be between 2 and 20")
    private String placeName;

    @NotNull(message = "Must be not null")
    private Long locationId;

    @NotNull(message = "Must be not null")
    private Long ownerId;
}
