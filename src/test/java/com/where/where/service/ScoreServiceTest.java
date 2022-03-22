package com.where.where.service;

import com.where.where.TestSupport;
import com.where.where.dto.CreateScoreRequest;
import com.where.where.dto.ScoreDto;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.*;
import com.where.where.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreServiceTest extends TestSupport {

    private ScoreRepository scoreRepository;
    private ScoreService scoreService;
    private ModelMapperService modelMapperService;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        scoreRepository = mock(ScoreRepository.class);
        modelMapperService = mock(ModelMapperService.class);
        modelMapper = mock(ModelMapper.class);
        scoreService = new ScoreService(scoreRepository, modelMapperService);
    }

    @Test
    void whenCreateScoreCalledWithValidRequest_itShouldReturnValidCreateScoreRequest() {
        CreateScoreRequest createScoreRequest = generateCreateScoreRequest();
        Score score = generateScore(createScoreRequest);

        //Determine mock services behavior regarding test scenario
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(score);
        when(scoreRepository.save(ArgumentMatchers.any(Score.class))).thenReturn(score);

        //Call the testing method
        Score result = scoreRepository.save(score);
        assertEquals(result, score);

        //Check results and verify the mock methods are called
        assertThat(result.getId()).isNotNull().isEqualTo(score.getId());
        assertThat(result.getCoronaScore()).isNotNull().isEqualTo(score.getCoronaScore());
        assertThat(result.getCoronaScore()).isNotNull().isEqualTo(score.getCoronaScore());
        assertThat(result.getVenueScore()).isNotNull().isEqualTo(score.getVenueScore());
        assertThat(result.getCreateDate()).isNotNull().isEqualTo(score.getCreateDate());

        assertThat(result.getCoronaScore()).isNotNegative().isEqualTo(score.getCoronaScore());
        assertThat(result.getVenueScore()).isNotNegative().isEqualTo(score.getVenueScore());

        assertTrue(result.getVenueScore() >= 0);
        assertTrue(result.getVenueScore() <= 5);
        assertTrue(result.getCoronaScore() >= 0);
        assertTrue(result.getCoronaScore() <= 5);

        verify(scoreRepository).save(score);
    }

    @Test
    void whenScoreIdCalled_itShouldReturnScoreDto() {
        Score score = generateScore();
        ScoreDto scoreDto = generateScoreDto(score);

        //Determine mock services behavior regarding test scenario
        when(modelMapperService.forDto()).thenReturn(modelMapper);
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(scoreDto);
        when(scoreRepository.getById(ArgumentMatchers.any())).thenReturn(score);

        //Call the testing method
        ScoreDto result = scoreService.getById(1L);

        //Check results and verify the mock methods are called
        assertEquals(result.getId(), score.getId());
        assertEquals(result.getVenueScore(), score.getVenueScore());
        assertEquals(result.getCoronaScore(), score.getCoronaScore());
        assertEquals(result.getCreateDate(), score.getCreateDate());

        assertTrue(result.getVenueScore() >= 0);
        assertTrue(result.getVenueScore() <= 5);
        assertTrue(result.getCoronaScore() >= 0);
        assertTrue(result.getCoronaScore() <= 5);

        verify(scoreRepository).getById(1L);
    }

  /*  @Test
    void GetAllScores_itShouldReturnScoreDtoList() {
        List<Score> scoreList = generateScores();
        List<ScoreDto> scoreDtoList = generateScoreDtoList(scoreList);

        //Determine mock services behavior regarding test scenario
        when(scoreRepository.findAll()).thenReturn(scoreList);
        when(modelMapperService.forDto()).thenReturn(modelMapper);
        when(scoreList.stream().map(score -> modelMapper.map(score, ScoreDto.class)).collect(Collectors.toList())).thenReturn(scoreDtoList);

        List<ScoreDto> expected = (scoreService.getAll());
        assertEquals(expected.get(1).getId(), scoreList.get(1).getId());

    }*/
}