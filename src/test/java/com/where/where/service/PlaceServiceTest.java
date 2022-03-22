package com.where.where.service;

import com.where.where.TestSupport;
import com.where.where.dto.PlaceDto;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.*;
import com.where.where.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaceServiceTest extends TestSupport {

    private PlaceRepository placeRepository;
    private ModelMapperService modelMapperService;
    private ModelMapper modelMapper;
    private PlaceService placeService;
    private PlaceCategoryService placeCategoryService;

    @BeforeEach
    void setUp() {
        modelMapperService = mock(ModelMapperService.class);
        modelMapper = mock(ModelMapper.class);
        placeRepository = mock(PlaceRepository.class);
        placeCategoryService = mock(PlaceCategoryService.class);
        placeService = new PlaceService(placeRepository, modelMapperService, placeCategoryService);
    }

    @Test
    void whenPlaceIdCalled_itShouldReturnPlaceDto() {
        Place place = generatePlace();
        PlaceDto placeDto = generatePlaceDto(place);

        //Determine mock services behavior regarding test scenario
        when(modelMapperService.forDto()).thenReturn(modelMapper);
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(placeDto);
        when(placeRepository.getById(ArgumentMatchers.any())).thenReturn(place);

        //Call the testing method
        PlaceDto result = placeService.getById(1L);

        //Check results and verify the mock methods are called
        assertEquals(result, placeDto);
        verify(placeRepository).getById(1L);

    }
}