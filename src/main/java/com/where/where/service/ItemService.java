package com.where.where.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.where.where.model.Item;
import com.where.where.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;

	public List<Item> getByMenuId(Long id) {
		return itemRepository.getByMenuId(id);
	}

	@Transactional
	public void deleteByMenuId(Long id) {
		itemRepository.deleteByMenuId(id);
	}

}
