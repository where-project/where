package com.where.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
	UserImage getByUserId(Long userId);

	boolean existsByUserId(Long userId);
}
