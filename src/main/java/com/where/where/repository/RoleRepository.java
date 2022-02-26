package com.where.where.repository;

import com.where.where.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
   // RoleRepository findByName(String name);
}