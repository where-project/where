package com.where.where.controller;

import com.where.where.dto.CreateLocationRequest;
import com.where.where.dto.LocationDto;
import com.where.where.dto.UpdateLocationRequest;
import com.where.where.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAll() {
        return new ResponseEntity<>(locationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long id) {
        return new ResponseEntity<>(locationService.getLocationById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateLocationRequest> add(@Valid @RequestBody CreateLocationRequest createLocationRequest) {
        return new ResponseEntity<>(locationService.add(createLocationRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateLocationRequest> update(@Valid @PathVariable Long id, UpdateLocationRequest updateLocationRequest) {
        return new ResponseEntity<UpdateLocationRequest>(locationService.update(id, updateLocationRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("id: " + id + " is deleted");
    }
}
