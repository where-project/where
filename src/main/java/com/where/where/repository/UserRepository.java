package com.where.where.repository;

import com.where.where.model.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BaseUser, Long> {
    BaseUser findByEmail(String username);
}
