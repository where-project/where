package com.where.where.service.user;

import java.util.List;

import com.where.where.dto.CreateUserRequest;
import com.where.where.dto.RoleToUserFormDto;
import com.where.where.dto.UserDto;
import com.where.where.model.BaseUser;
import com.where.where.model.Role;
import com.where.where.model.User;

public interface UserService {
	UserDto saveUser(CreateUserRequest createUserRequest);

	Role saveRole(Role role);

	void addRoleToUser(RoleToUserFormDto form);

	BaseUser getUser(String username);

	List<BaseUser> getUsers();

	void existsById(Long id);

	User getByUserId(Long id);
}
