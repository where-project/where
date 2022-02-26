package com.where.where.mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto();
	ModelMapper forRequest();
}
