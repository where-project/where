package com.where.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.BaseUser;

public interface UserRepository extends JpaRepository<BaseUser, Long> {
    BaseUser findByEmail(String username);

    BaseUser findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
