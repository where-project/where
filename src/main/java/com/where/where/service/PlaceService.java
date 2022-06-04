package com.where.where.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.where.where.dto.CreatePlaceCategoryRequest;
import com.where.where.dto.CreatePlaceRequest;
import com.where.where.dto.PlaceDto;
import com.where.where.dto.UpdatePlaceRequest;
import com.where.where.dto.model.CreatePlaceModel;
import com.where.where.exception.BusinessException;
import com.where.where.exception.PlaceNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Item;
import com.where.where.model.Location;
import com.where.where.model.Menu;
import com.where.where.model.Place;
import com.where.where.model.PlaceCategory;
import com.where.where.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;
	private final ModelMapperService modelMapperService;
	private final PlaceCategoryService placeCategoryService;
	private final CategoryService categoryService;

	@Transactional
	public CreatePlaceRequest add(CreatePlaceModel createPlaceModel) {
		checkIfCategoryExists(createPlaceModel.getCreatePlaceRequest().getCreatePlaceCategoryRequests());

		Place place = this.modelMapperService.forRequest().map(createPlaceModel.getCreatePlaceRequest(), Place.class);
		List<PlaceCategory> placeCategories = createPlaceModel.getCreatePlaceRequest().getCreatePlaceCategoryRequests().stream()
				.map(placeCategory -> modelMapperService.forRequest().map(placeCategory, PlaceCategory.class))
				.collect(Collectors.toList());
		Location location = this.modelMapperService.forRequest().map(createPlaceModel.getCreateLocationRequest(), Location.class);
		
		List<Item> items = createPlaceModel.getCreateItemRequest().stream().map(item -> modelMapperService.forRequest().map(item, Item.class))
				.collect(Collectors.toList());
		
		Menu menu = new Menu();
		menu.setItems(items);
		menu.setPlace(place);
		items.forEach(item -> item.setMenu(menu));

		place.setLocation(location);
		place.setMenu(menu);
		
		place.setPlaceCategories(mappingPlaceCategory(placeCategories, place));
		place.setCreationDate(LocalDate.now());

		placeRepository.save(place);
		return createPlaceModel.getCreatePlaceRequest();
	}

	private void checkIfCategoryExists(List<CreatePlaceCategoryRequest> createPlaceCategoryRequests) {
		createPlaceCategoryRequests.forEach(createPlaceCategoryRequest -> {
			categoryService.checkIfCategoryExistsById(createPlaceCategoryRequest.getCategoryId());
		});
	}

	private List<PlaceCategory> mappingPlaceCategory(List<PlaceCategory> placeCategories, Place place) {
		if (!placeCategories.isEmpty()) {
			for (PlaceCategory placeCategory : placeCategories) {
				placeCategory.setId(null);
				placeCategory.setPlace(place);
			}
			return placeCategories;
		}
		throw new BusinessException("Category id is not specified.");
	}

	public List<PlaceDto> getAll() {
		List<Place> result = placeRepository.findAll();
		List<PlaceDto> response = result.stream().map(place -> modelMapperService.forDto().map(place, PlaceDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public UpdatePlaceRequest update(Long id, UpdatePlaceRequest updatePlaceDto) {
		checkIfPlaceExists(id);
		checkIfCategoryExists(updatePlaceDto.getCreatePlaceCategoryRequests());
		Place oldPlace = placeRepository.getById(id);
		Place place = modelMapperService.forRequest().map(updatePlaceDto, Place.class);
		placeCategoryService.deleteByPlaceId(id);
		List<PlaceCategory> placeCategories = updatePlaceDto.getCreatePlaceCategoryRequests().stream()
				.map(placeCategory -> modelMapperService.forRequest().map(placeCategory, PlaceCategory.class))
				.collect(Collectors.toList());
		place.setPlaceCategories(mappingPlaceCategory(placeCategories, place));
		place.setId(id);
		place.setCreationDate(oldPlace.getCreationDate());
		placeRepository.save(place);
		return updatePlaceDto;
	}

	public Boolean changeActive(Long placeId) {
		checkIfPlaceExists(placeId);
		Place placeToUpdate = placeRepository.getById(placeId);
		placeToUpdate.setActive(!placeToUpdate.isActive());
		placeRepository.save(placeToUpdate);
		return placeToUpdate.isActive();
	}

	public Boolean changeStatus(Long placeId) {
		checkIfPlaceExists(placeId);
		Place placeToUpdate = placeRepository.getById(placeId);
		placeToUpdate.setStatus(!placeToUpdate.isStatus());
		placeRepository.save(placeToUpdate);
		return placeToUpdate.isStatus();
	}

	public Boolean checkIsActive(Long placeId) {
		checkIfPlaceExists(placeId);
		PlaceDto placeDto = getById(placeId);
		return placeDto.isActive();
	}

	public Boolean checkIsStatus(Long placeId) {
		checkIfPlaceExists(placeId);
		PlaceDto placeDto = getById(placeId);
		return placeDto.isStatus();
	}

	public void checkIfPlaceExists(Long id) {
		if (!placeRepository.existsById(id)) {
			throw new PlaceNotFoundException("Venue not found.");
		}
	}

	public PlaceDto getById(Long id) {
		checkIfPlaceExists(id);
		Place place = placeRepository.getById(id);
		PlaceDto response = modelMapperService.forDto().map(place, PlaceDto.class);
		return response;
	}

	public void delete(Long id) {
		checkIfPlaceExists(id);
		placeRepository.deleteById(id);

	}
}
