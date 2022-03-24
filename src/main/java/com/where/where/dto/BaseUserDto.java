package com.where.where.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDto {

    @NotNull(message = "Must be not null")
    @NotBlank(message = "Must be not blank")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Email must be not null")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null")
    @Column(name = "password")
    private String password;
}
