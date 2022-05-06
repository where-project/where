package com.where.where.service;

import org.springframework.stereotype.Service;

import com.where.where.exception.BusinessException;
import com.where.where.model.MenuType;
import com.where.where.repository.MenuTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuTypeService {
	private final MenuTypeRepository menuTypeRepository;

	public void checkIfMenuTypeExists(Long id) {
		if (!menuTypeRepository.existsById(id)) {
			throw new BusinessException("MenuType not found.");
		}
	}

	public MenuType getById(Long id) {
		checkIfMenuTypeExists(id);
		return menuTypeRepository.getById(id);
	}

}
