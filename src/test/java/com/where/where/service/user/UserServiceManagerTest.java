package com.where.where.service.user;

import com.where.where.mapper.ModelMapperService;
import com.where.where.model.BaseUser;
import com.where.where.model.Role;
import com.where.where.repository.RoleRepository;
import com.where.where.repository.ScoreRepository;
import com.where.where.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UserServiceManagerTest {

    private UserServiceManager userServiceManager;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);

        userServiceManager = new UserServiceManager(userRepository,roleRepository,passwordEncoder);
    }

    @Test
    void given_user_when_createNewUser_will_return_user() {
        BaseUser user = new BaseUser();
        user.setEmail("hello@gmail.com");
        user.setUsername("onur");
        user.setPassword("123");
        user.setId(1L);

        given(passwordEncoder.encode(user.getPassword())).willReturn("1234");
        given(userRepository.save(user)).willReturn(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        BaseUser result = userServiceManager.saveUser(user);
        assertEquals(result,user);

        assertThat(result.getUsername()).isNotBlank().isEqualTo(user.getUsername());
        assertThat(result.getUsername()).isNotNull().isEqualTo(user.getUsername());
        //access = JsonProperty.Access.WRITE_ONLY
        assertThat(result.getPassword()).isNull();
        assertThat(result.getId()).isNotNull().isEqualTo(user.getId());
        assertThat(result.getEmail()).isNotNull().isEqualTo(user.getEmail());
    }

    @Test
    void given_role_when_createNewRole_will_return_role() {
        Role role = new Role();
        role.setName("ADMIN");
        role.setId(1L);
        given(roleRepository.save(role)).willReturn(role);
        Role result = userServiceManager.saveRole(role);

        assertEquals(result,role);
        assertThat(result.getId()).isNotNull().isEqualTo(role.getId());
        assertThat(result.getName()).isNotNull().isEqualTo(role.getName());
        assertThat(result.getName()).isNotBlank().isEqualTo(role.getName());
    }
}