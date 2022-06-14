package com.where.where.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.where.where.dto.CreateScoreRequest;
import com.where.where.dto.ScoreDto;
import com.where.where.exception.ScoreNotFoundException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Score;
import com.where.where.repository.ScoreRepository;
import com.where.where.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final ModelMapperService modelMapperService;
    @Lazy
    private final PlaceService placeService;
    private final UserService userService;

    @Transactional
    public Score add(CreateScoreRequest createScoreRequest) {
        checkIfUserExists(createScoreRequest.getUserId());
        checkIfPlaceExists(createScoreRequest.getPlaceId());
        Score score = modelMapperService.forRequest().map(createScoreRequest, Score.class);
        score.setCreateDate(LocalDate.now());
        return scoreRepository.save(score);
    }

    public List<ScoreDto> getAll() {
        List<Score> result = scoreRepository.findAll();
        List<ScoreDto> response = result.stream().map(score -> modelMapperService.forDto().map(score, ScoreDto.class))
                .collect(Collectors.toList());
        return response;
    }

    public ScoreDto getById(Long id) {
        checkIfScoreExists(id);
        Score score = scoreRepository.getById(id);
        ScoreDto response = modelMapperService.forDto().map(score, ScoreDto.class);
        return response;
    }

    public void delete(Long id) {
        checkIfScoreExists(id);
        scoreRepository.deleteById(id);
    }

    public CreateScoreRequest update(Long id, CreateScoreRequest updateScoreDto) {
        checkIfScoreExists(id);
        checkIfUserExists(updateScoreDto.getUserId());
        checkIfPlaceExists(updateScoreDto.getPlaceId());
        Score score = modelMapperService.forRequest().map(updateScoreDto, Score.class);
        score.setId(id);
        scoreRepository.save(score);
        return updateScoreDto;
    }

    private void checkIfPlaceExists(Long id) {
        placeService.checkIfPlaceExists(id);
    }

    private void checkIfUserExists(Long id) {
        userService.existsById(id);
    }

    private void checkIfScoreExists(Long id) {
        if (!scoreRepository.existsById(id)) {
            throw new ScoreNotFoundException("Score not found");
        }
    }

    public List<ScoreDto> getByPlaceId(Long id) {
        checkIfPlaceExists(id);
        List<Score> result = scoreRepository.getByPlaceId(id);
        return result.stream().map(score -> modelMapperService.forDto().map(score, ScoreDto.class))
                .collect(Collectors.toList());
    }

}
