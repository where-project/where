package com.where.where.service;

import com.where.where.dto.CreateBusinessHourRequest;
import com.where.where.dto.CreateCategoryRequest;
import com.where.where.exception.PlaceNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.BusinessHour;
import com.where.where.model.Category;
import com.where.where.model.FavoritePlace;
import com.where.where.model.Item;
import com.where.where.repository.BusinessHourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessHourService {
    private final BusinessHourRepository businessHourRepository;
    private final PlaceService placeService;
    private final ModelMapperService modelMapperService;
    @Transactional
    public  List<CreateBusinessHourRequest> add(List<CreateBusinessHourRequest> createBusinessHourRequest) {
        placeService.checkIfPlaceExists(createBusinessHourRequest.get(0).getPlaceId());
        List<BusinessHour> businessHours = createBusinessHourRequest.stream()
                .map(item -> modelMapperService.forRequest().map(item, BusinessHour.class)).collect(Collectors.toList());
      businessHourRepository.saveAll(businessHours);
        return createBusinessHourRequest;
    }
}
