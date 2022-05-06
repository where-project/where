package com.where.where.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.CreateItemRequest;
import com.where.where.dto.MenuDto;
import com.where.where.dto.model.MenuModel;
import com.where.where.service.MenuService;

@RestController
@RequestMapping("api/v1/menus")
public class MenuController {
	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@PostMapping("/add")
	public ResponseEntity<MenuDto> add(@Valid @RequestBody MenuModel menuModel) {
		return new ResponseEntity<MenuDto>(menuService.add(menuModel), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<MenuDto>> getAll() {
		return new ResponseEntity<>(menuService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<MenuDto> getById(@PathVariable Long id) {
		return new ResponseEntity<>(menuService.getById(id), HttpStatus.OK);
	}

	@GetMapping("/getByPlaceId/{id}")
	public ResponseEntity<List<MenuDto>> getByPlaceId(@PathVariable Long id) {
		return new ResponseEntity<>(menuService.getByPlaceId(id), HttpStatus.OK);
	}

	@PutMapping("/{placeId}/{menuTypeId}")
	public ResponseEntity<MenuDto> update(@Valid @PathVariable Long placeId, @Valid @PathVariable Long menuTypeId,
			@Valid @RequestBody List<CreateItemRequest> createItemRequest) {
		return new ResponseEntity<MenuDto>(menuService.update(placeId, menuTypeId, createItemRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		menuService.delete(id);
		return ResponseEntity.ok("id: " + id + " is deleted");
	}
}
