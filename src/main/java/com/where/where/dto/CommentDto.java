package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;

	@NotNull(message = "Comment text must be not null")
	@NotBlank(message = "Comment text must be not blank")
	private String commentText;

	@NotNull(message = "Create date must be not null")
	@NotBlank(message = "Create date must be not blank")
	private String createDate;

	@NotNull(message = "First name must be not null")
	@NotBlank(message = "First name must be not blank")
	private String firstName;

	@NotNull(message = "Last name must be not null")
	@NotBlank(message = "Last name must be not blank")
	private String lastName;

	@NotNull(message = "Place name must be not null")
	@NotBlank(message = "Place name must be not blank")
	private String placeName;
}
