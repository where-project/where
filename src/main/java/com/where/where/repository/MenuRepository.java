package com.where.where.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.where.where.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> getByPlaceId(Long id);

	@Query("SELECT m FROM Menu m WHERE m.place.id=?1 AND m.menuType.id=?2")
	Menu getByMenuTypeIdAndPlaceId(Long placeId, Long menuTypeId);
}
