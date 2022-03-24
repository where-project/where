
package com.where.where.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

	@NotBlank(message = "Must be not blank")
	private String categoryName;
}
