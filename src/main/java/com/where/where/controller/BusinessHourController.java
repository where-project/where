package com.where.where.controller;

import com.where.where.dto.CreateBusinessHourRequest;
import com.where.where.model.BusinessHour;
import com.where.where.service.BusinessHourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/business-hours")
@RequiredArgsConstructor
public class BusinessHourController {
    private final BusinessHourService businessHourService;

    @PostMapping("/add")
    public ResponseEntity<List<CreateBusinessHourRequest>> add(@RequestBody List<CreateBusinessHourRequest> businessHour) {
        return new ResponseEntity<List<CreateBusinessHourRequest>>(businessHourService.add(businessHour), HttpStatus.OK);
    }
}
