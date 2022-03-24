package com.where.where.service.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.where.where.dto.BaseUserDto;
import com.where.where.exception.*;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Comment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.where.where.model.BaseUser;
import com.where.where.model.Role;
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
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public BaseUserDto saveUser(BaseUser baseUser) {
        if (userRepository.existsByUsername(baseUser.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists ");
        }
        if (userRepository.existsByEmail(baseUser.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        baseUser.getRoles().forEach(role -> {
            if (!roleRepository.existsById(role.getId())) {
                throw new RoleNotFoundException("Role not found");
            }
        });

        BaseUserDto userDto = this.modelMapperService.forDto().map(baseUser, BaseUserDto.class);
        baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
        userRepository.save(baseUser);
        log.info("Saving new user {] to the database", baseUser.getUsername());
        return userDto;
    }

    @Override
    public Role saveRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RoleAlreadyExistsException("Role already exists");
        }
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        if (!userRepository.existsByUsername(username)) {
            throw new UserNotFoundException("Username not exists");
        } else if (!roleRepository.existsByName(roleName)) {
            throw new RoleNotFoundException("Role not found");
        }
        log.info("Adding role {} to user{}", roleName, username);
        BaseUser user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info(role.toString());
        user.getRoles().forEach(rolee -> {
            if (role.getId() == rolee.getId()) {
                throw new RoleAlreadyExistsException(user.getUsername() + " already has role " + rolee.getName());
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
}
