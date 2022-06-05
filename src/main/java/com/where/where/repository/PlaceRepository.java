package com.where.where.repository;

import com.where.where.model.Place;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

	List<Place> findByPlaceCategoriesCategoryId(Long categoryId);

	List<Place> findByLocationCityId(Long cityId);

	List<Place> findByLocationCityIdAndPlaceCategoriesCategoryId(Long cityId, Long categoryId);

}
