package com.where.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.where.where.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
