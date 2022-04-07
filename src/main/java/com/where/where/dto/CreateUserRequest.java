package com.where.where.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	@NotNull(message = "Username must be not null")
	@NotBlank(message = "Username must be not blank")
	private String username;

	@NotNull(message = "Email must be not null")
	@Email(message = "Email should be valid")
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Password must be not null")
	private String password;

	private List<CreateUserRoleRequest> createUserRoleRequest;

	@NotNull(message = "First name must be not null")
	@NotBlank(message = "First name must be not blank")
	private String firstName;

	@NotNull(message = "Last name must be not null")
	@NotBlank(message = "Last name must be not blank")
	private String lastName;

}
