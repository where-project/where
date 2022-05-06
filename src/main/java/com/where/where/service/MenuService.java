package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.where.where.dto.CreateItemRequest;
import com.where.where.dto.MenuDto;
import com.where.where.dto.model.MenuModel;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Item;
import com.where.where.model.Menu;
import com.where.where.model.Place;
import com.where.where.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;
	private final ModelMapperService modelMapperService;
	private final PlaceService placeService;
	private final MenuTypeService menuTypeService;
	private final ItemService itemService;

	public MenuDto add(MenuModel menuModel) {
		checkIfPlaceExists(menuModel.getPlaceId());
		checkIfMenuTypeExists(menuModel.getMenuTypeId());

		List<Item> items = menuModel.getCreateItemRequest().stream()
				.map(item -> modelMapperService.forDto().map(item, Item.class)).collect(Collectors.toList());
		Menu menu = new Menu();

		Place place = modelMapperService.forRequest().map(placeService.getById(menuModel.getPlaceId()), Place.class);

		menu.setPlace(place);
		menu.setItems(items);

		items.forEach(item -> item.setMenu(menu));
		menu.setMenuType(menuTypeService.getById(menuModel.getMenuTypeId()));
		menuRepository.save(menu);
		return modelMapperService.forDto().map(menu, MenuDto.class);
	}

	private void checkIfPlaceExists(Long id) {
		placeService.checkIfPlaceExists(id);
	}

	private void checkIfMenuTypeExists(Long id) {
		menuTypeService.checkIfMenuTypeExists(id);
	}

	public List<MenuDto> getAll() {
		List<Menu> result = menuRepository.findAll();
		return result.stream().map(menu -> modelMapperService.forDto().map(menu, MenuDto.class))
				.collect(Collectors.toList());

	}

	public MenuDto getById(Long id) {
		checkIfMenuExistsById(id);
		Menu menu = menuRepository.getById(id);
		return modelMapperService.forDto().map(menu, MenuDto.class);
	}

	public List<MenuDto> getByPlaceId(Long id) {
		checkIfPlaceExists(id);
		List<Menu> menuList = menuRepository.getByPlaceId(id);
		return menuList.stream().map(menu -> modelMapperService.forDto().map(menu, MenuDto.class))
				.collect(Collectors.toList());

	}

	public void delete(Long id) {
		checkIfMenuExistsById(id);
		menuRepository.deleteById(id);
	}

	@Transactional
	public MenuDto update(Long id, Long menuTypeId, List<CreateItemRequest> createItemRequest) {
		checkIfPlaceExists(id);
		checkIfMenuTypeExists(menuTypeId);

		Menu menu = menuRepository.getByMenuTypeIdAndPlaceId(id, menuTypeId);
		List<Item> items = createItemRequest.stream().map(item -> modelMapperService.forDto().map(item, Item.class))
				.collect(Collectors.toList());

		// old menu items will be deleted..
		// it is not necessary
		// this code block can remove later
		itemService.deleteByMenuId(menu.getId());

		menu.setItems(items);
		items.forEach(item -> {
			item.setMenu(menu);
		});

		menuRepository.save(menu);
		return modelMapperService.forDto().map(menu, MenuDto.class);
	}

	public void checkIfMenuExistsById(Long id) {
		if (!menuRepository.existsById(id)) {
			throw new BusinessException("Menu not found.");
		}
	}

}
