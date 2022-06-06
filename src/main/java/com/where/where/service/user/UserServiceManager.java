package com.where.where.service.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.where.where.dto.CreateRoleRequest;
import com.where.where.dto.CreateUserRequest;
import com.where.where.dto.RoleToUserFormDto;
import com.where.where.dto.UserDto;
import com.where.where.exception.EmailAlreadyExistsException;
import com.where.where.exception.RoleAlreadyExistsException;
import com.where.where.exception.RoleNotFoundException;
import com.where.where.exception.UserNotFoundException;
import com.where.where.exception.UsernameAlreadyExistsException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.BaseUser;
import com.where.where.model.Role;
import com.where.where.model.User;
import com.where.where.repository.RoleRepository;
import com.where.where.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceManager implements UserService, UserDetailsService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapperService modelMapperService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BaseUser user = userRepository.findByUsername(username);
		if (user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the database: {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public UserDto saveUser(CreateUserRequest createUserRequest) {
		if (userRepository.existsByUsername(createUserRequest.getUsername())) {
			throw new UsernameAlreadyExistsException("Username already exists ");
		}
		if (userRepository.existsByEmail(createUserRequest.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		createUserRequest.getCreateUserRoleRequest().forEach(role -> {
			if (!roleRepository.existsById(role.getId())) {
				throw new RoleNotFoundException("Role not found");
			}
		});

		createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
		List<Role> rolesList = createUserRequest.getCreateUserRoleRequest().stream()
				.map(role -> modelMapperService.forRequest().map(role, Role.class)).collect(Collectors.toList());
		user.setRoles(rolesList);
		userRepository.save(user);
		log.info("Saving new user {] to the database", createUserRequest.getUsername());
		UserDto userDto = this.modelMapperService.forDto().map(createUserRequest, UserDto.class);
		return userDto;
	}

	@Override
	public Role saveRole(CreateRoleRequest createRoleRequest) {
		if (roleRepository.existsByName(createRoleRequest.getName())) {
			throw new RoleAlreadyExistsException("Role already exists");
		}
		log.info("Saving new role {} to the database", createRoleRequest.getName());
		Role role = modelMapperService.forRequest().map(createRoleRequest, Role.class);
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(RoleToUserFormDto form) {

		if (!userRepository.existsById(form.getUserId())) {
			throw new UserNotFoundException("Username not exists");
		} else if (!roleRepository.existsById(form.getRoleId())) {
			throw new RoleNotFoundException("Role not found");
		}

		BaseUser user = userRepository.getById(form.getUserId());
		Role role = roleRepository.getById(form.getRoleId());

		log.info("Adding role {} to user{}", role.getName(), user.getUsername());
		log.info(role.toString());

		user.getRoles().forEach(userRole -> {
			if (role.getId() == userRole.getId()) {
				throw new RoleAlreadyExistsException(user.getUsername() + " already has role " + userRole.getName());
			}
		});

		user.getRoles().add(role);
	}

	@Override
	public BaseUser getUser(String username) {
		log.info("Fetching user {}", username);
		return userRepository.findByUsername(username);
	}

	@Override
	public List<BaseUser> getUsers() {
		log.info("Fetching all user");
		return userRepository.findAll();
	}

	@Override
	public void existsById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User not found.");
		}
	}

	public void existsByUsername(String username) {
		if (!userRepository.existsByUsername(username)) {
			throw new UserNotFoundException("User not found.");
		}

	}

	@Override
	public UserDto getById(Long id) {
		existsById(id);
		BaseUser baseUser = userRepository.getById(id);
		UserDto response = modelMapperService.forDto().map(baseUser, UserDto.class);
		return response;
	}

	@Override
	public UserDto getByUsername(String username) {
		existsByUsername(username);
		BaseUser baseUser = userRepository.findByUsername(username);
		UserDto response = modelMapperService.forDto().map(baseUser, UserDto.class);
		return response;
	}
}
