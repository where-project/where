package com.where.where.controller;

import com.where.where.dto.CreateFavoritePlaceRequest;
import com.where.where.dto.FavoritePlaceDto;
import com.where.where.service.FavoritePlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite-places")
@RequiredArgsConstructor
public class FavoritePlaceController {

    private final FavoritePlaceService favoritePlaceService;

    @PostMapping("/add")
    public ResponseEntity<CreateFavoritePlaceRequest> add(@Valid
                                                          @RequestBody CreateFavoritePlaceRequest createFavoritePlaceRequest) {
        return new ResponseEntity<CreateFavoritePlaceRequest>(
                favoritePlaceService.add(createFavoritePlaceRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FavoritePlaceDto>> getFavoritePlacesByUserId(@PathVariable Long id) {
        return new ResponseEntity<List<FavoritePlaceDto>>(favoritePlaceService.getByUserId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        favoritePlaceService.delete(id);
    }
}
