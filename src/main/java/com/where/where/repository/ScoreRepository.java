package com.where.where.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> getByPlaceId(Long id);

    @Query("SELECT AVG(s.venueScore) FROM Score s WHERE s.place.id in :id")
    float getAverageScore(@Param("id") Long id);

    @Query("SELECT COUNT(s) FROM Score s WHERE s.place.id in :id")
    Long getNumberOfReview(@Param("id") Long id);
}
