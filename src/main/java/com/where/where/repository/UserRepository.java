package com.where.where.repository;

import com.where.where.model.BaseUser;
import com.where.where.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<BaseUser, Long> {
    BaseUser findByEmail(String username);

    BaseUser findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
