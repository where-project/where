package com.where.where.service.user;


import com.where.where.model.BaseUser;
import com.where.where.model.Role;

import java.util.List;

public interface UserService {
    BaseUser saveUser(BaseUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    BaseUser getUser(String username);

    List<BaseUser> getUsers();
}