package com.where.where.repository;

import com.where.where.model.BusinessHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHourRepository extends JpaRepository<BusinessHour, Long> {
}
