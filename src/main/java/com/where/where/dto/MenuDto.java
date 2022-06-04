package com.where.where.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

	private Long id;

	private Long placeId;

	private List<ItemDto> items;
}
