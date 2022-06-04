package com.where.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	Menu getByPlaceId(Long id);
}
