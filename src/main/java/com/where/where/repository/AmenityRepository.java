package com.where.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.Amenity;

public interface AmenityRepository  extends JpaRepository<Amenity, Long> {

}
