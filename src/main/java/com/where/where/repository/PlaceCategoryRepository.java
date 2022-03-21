package com.where.where.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.PlaceCategory;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Long> {
	List<PlaceCategory> findByPlace_Id(Long id);

}
