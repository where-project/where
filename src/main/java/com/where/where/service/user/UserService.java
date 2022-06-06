package com.where.where.service.user;

import java.util.List;

import com.where.where.dto.CreateRoleRequest;
import com.where.where.dto.CreateUserRequest;
import com.where.where.dto.RoleToUserFormDto;
import com.where.where.dto.UserDto;
import com.where.where.model.BaseUser;
import com.where.where.model.Role;

public interface UserService {

	UserDto saveUser(CreateUserRequest createUserRequest);

	Role saveRole(CreateRoleRequest createRoleRequest);

	void addRoleToUser(RoleToUserFormDto form);

	BaseUser getUser(String username);

	List<BaseUser> getUsers();

	void existsById(Long id);

	UserDto getById(Long id);
	
	UserDto getByUsername(String username);


}
