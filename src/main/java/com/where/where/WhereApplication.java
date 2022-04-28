package com.where.where;

import java.util.Arrays;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.where.where.service.user.UserService;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class WhereApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			/*
			 * userService.saveRole(new Role(null, RoleName.ROLE_USER));
			 * userService.saveRole(new Role(null, RoleName.ROLE_ADMIN));
			 * userService.saveRole(new Role(null, RoleName.ROLE_OWNER));
			 */

			/*
			 * userService.saveUser(new BaseUser(null, "John Travolta", "john@gmail.com",
			 * "231", new ArrayList<>())); userService.saveUser(new BaseUser(null,
			 * "Onur Akkepenek", "onur@gmail.com", "32321", new ArrayList<>()));
			 * userService.saveUser(new BaseUser(null, "Jim Carry", "jim@gmail.com",
			 * "12311", new ArrayList<>())); userService.saveUser(new BaseUser(null,
			 * "Arnold Travolta", "arnold@gmail.com", "1123213", new ArrayList<>()));
			 */
			/*
			 * userService.saveUser(new BaseUser(null, "Onur", "onur1@gmail.com", "123", new
			 * ArrayList<>()));
			 * 
			 * /*userService.addRoleToUser("Onur Akkepenek", "ROLE_ADMIN");
			 * userService.addRoleToUser("John Travolta", "ROLE_ADMIN");
			 * userService.addRoleToUser("John Travolta", "ROLE_OWNER");
			 * userService.addRoleToUser("Jim Carry", "ROLE_OWNER");
			 * userService.addRoleToUser("Jim Carry", "ROLE_ADMIN");
			 */
			/*
			 * userService.addRoleToUser("Onur", "ROLE_ADMIN");
			 * userService.addRoleToUser("Onur", "ROLE_OWNER");
			 */

			// userService.addRoleToUser("Yase", "ROLE_USER");

		};
	}

	@Bean
	public OpenAPI myAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearer-key",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("My API").description("Documentation of API v.1.0").version("1.0"))
				.addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"))
						.addList("bearer-key", Collections.emptyList()));
	}
}
