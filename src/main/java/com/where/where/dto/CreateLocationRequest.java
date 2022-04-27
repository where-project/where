package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationRequest {
    @NotNull(message = "Must be not null")
    @Positive
    private double lat;

    @NotNull(message = "Must be not null")
    @Positive
    private double lng;

    @NotBlank(message = "Must be not blank")
    private String country;

    @NotBlank(message = "Must be not blank")
    private String city;

    @NotBlank(message = "Must be not blank")
    private String address;
}