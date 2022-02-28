package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {

    @NotNull(message = "Must be not null")
    @Size(max = 20, min = 2, message = "Must be between 2 and 20")
    private String commentText;

    @NotNull(message = "Must be not null")
    @NotBlank(message = "Must be not blank")
    private String createDate;

    @NotNull(message = "Must be not null")
    private Long userId;

    @NotNull(message = "Must be not null")
    private Long placeId;
}
