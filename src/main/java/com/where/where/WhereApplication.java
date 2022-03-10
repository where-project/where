package com.where.where;

import com.where.where.model.BaseUser;
import com.where.where.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

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
          /*  userService.saveRole(new Role(null, RoleName.ROLE_USER));
            userService.saveRole(new Role(null, RoleName.ROLE_ADMIN));
            userService.saveRole(new Role(null, RoleName.ROLE_OWNER));*/

            /*userService.saveUser(new BaseUser(null, "John Travolta", "john@gmail.com", "231", new ArrayList<>()));
            userService.saveUser(new BaseUser(null, "Onur Akkepenek", "onur@gmail.com", "32321", new ArrayList<>()));
            userService.saveUser(new BaseUser(null, "Jim Carry", "jim@gmail.com", "12311", new ArrayList<>()));
            userService.saveUser(new BaseUser(null, "Arnold Travolta", "arnold@gmail.com", "1123213", new ArrayList<>()));*/
           /* userService.saveUser(new BaseUser(null, "Onur", "onur1@gmail.com", "123", new ArrayList<>()));

            /*userService.addRoleToUser("Onur Akkepenek", "ROLE_ADMIN");
            userService.addRoleToUser("John Travolta", "ROLE_ADMIN");
            userService.addRoleToUser("John Travolta", "ROLE_OWNER");
            userService.addRoleToUser("Jim Carry", "ROLE_OWNER");
            userService.addRoleToUser("Jim Carry", "ROLE_ADMIN");*/
            /*userService.addRoleToUser("Onur", "ROLE_ADMIN");
            userService.addRoleToUser("Onur", "ROLE_OWNER");*/

            //userService.addRoleToUser("Yase", "ROLE_USER");


        };
    }
}
