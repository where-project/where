package com.where.where.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.where.where.dto.CreateScoreRequest;
import com.where.where.dto.ScoreDto;
import com.where.where.exception.ScoreNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Score;
import com.where.where.repository.ScoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService {
	private final ScoreRepository scoreRepository;
	private final ModelMapperService modelMapperService;

	public CreateScoreRequest add(CreateScoreRequest createScoreRequest) {
		Score score = modelMapperService.forRequest().map(createScoreRequest, Score.class);
		scoreRepository.save(score);
		return createScoreRequest;
	}

	public List<ScoreDto> getAll() {
		List<Score> result = scoreRepository.findAll();
		List<ScoreDto> response = result.stream().map(score -> modelMapperService.forDto().map(score, ScoreDto.class))
				.collect(Collectors.toList());
		return response;
	}

	public ScoreDto getById(Long id) {
		Score score = scoreRepository.getById(id);
		ScoreDto response = modelMapperService.forDto().map(score, ScoreDto.class);
		return response;
	}

	public void delete(Long id) {
		scoreRepository.deleteById(id);
	}

	public CreateScoreRequest update(Long id, CreateScoreRequest updateScoreDto) {
		if (scoreRepository.existsById(id)) {
			Score score = modelMapperService.forRequest().map(updateScoreDto, Score.class);
			score.setId(id);
			scoreRepository.save(score);
			return updateScoreDto;
		}
		throw new ScoreNotFoundException("Score does not found.");

	}
}
