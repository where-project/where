package com.where.where.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	List<Score> getByPlaceId(Long id);
}
