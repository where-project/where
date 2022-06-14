package com.where.where.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long id;

	private String commentText;

	private LocalDate createDate;

	private String firstName;

	private String lastName;

	private String placeName;

	private Long userId;

	private Long scoreId;
}
