package com.where.where.repository;

import com.where.where.model.FavoritePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {

    List<FavoritePlace> getByUserId(Long id);

    @Query("SELECT new java.lang.Boolean(count(*) > 0) " +
            "FROM FavoritePlace e " +
            "WHERE place_id=?1 " +
            "AND user_id = ?2")
    Boolean existsFavoritePlace(Long placeId, Long userId);
}
