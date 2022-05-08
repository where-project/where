package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CreateLocationRequest;
import com.where.where.dto.LocationDto;
import com.where.where.dto.UpdateLocationRequest;
import com.where.where.exception.LocationNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Location;
import com.where.where.repository.LocationRepository;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final ModelMapperService modelMapperService;

    public LocationService(LocationRepository locationRepository, ModelMapperService modelMapperService) {
        this.locationRepository = locationRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<LocationDto> getAll() {
        List<Location> result = locationRepository.findAll();
        List<LocationDto> response = result.stream()
                .map(location -> modelMapperService.forDto().map(location, LocationDto.class))
                .collect(Collectors.toList());
        return response;
    }

    public CreateLocationRequest add(CreateLocationRequest createLocationRequest) {
        Location location = modelMapperService.forRequest().map(createLocationRequest, Location.class);
        locationRepository.save(location);
        return createLocationRequest;
    }

    public LocationDto getLocationById(Long id) {
        checkIfLocationExists(id);
        Location location = locationRepository.getById(id);
        return modelMapperService.forDto().map(location, LocationDto.class);
    }

    public void deleteLocation(Long id) {
        checkIfLocationExists(id);
        locationRepository.deleteById(id);
    }

    public UpdateLocationRequest update(Long id, UpdateLocationRequest updateLocationRequest) {
        checkIfLocationExists(id);
        Location location = modelMapperService.forRequest().map(updateLocationRequest, Location.class);
        location.setId(id);
        locationRepository.save(location);
        return updateLocationRequest;
    }

    private void checkIfLocationExists(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new LocationNotFoundException("Location not found");
        }
    }
}
