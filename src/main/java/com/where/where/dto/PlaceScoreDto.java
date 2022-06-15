package com.where.where.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceScoreDto {
    ScoreResponseRequest scoreResponseRequest;
    PlaceDto placeDto;
}
