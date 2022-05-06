package com.where.where.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.where.where.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> getByMenuId(Long id);

	void deleteByMenuId(Long id);
	
}
