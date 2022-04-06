package com.where.where.service;

import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateFavoritePlaceRequest;
import com.where.where.dto.FavoritePlaceDto;
import com.where.where.exception.CommentNotFoundException;
import com.where.where.exception.FavoritePlaceAlreadyExistsException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.Comment;
import com.where.where.model.FavoritePlace;
import com.where.where.repository.FavoritePlaceRepository;
import com.where.where.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FavoritePlaceService {
    private final FavoritePlaceRepository favoritePlaceRepository;
    private final ModelMapperService modelMapperService;
    private final PlaceService placeService;
    private final UserService userService;


    public FavoritePlaceService(FavoritePlaceRepository favoritePlaceRepository, ModelMapperService modelMapperService, PlaceService placeService, UserService userService) {
        this.favoritePlaceRepository = favoritePlaceRepository;
        this.modelMapperService = modelMapperService;
        this.placeService = placeService;
        this.userService = userService;
    }

    public CreateFavoritePlaceRequest add(CreateFavoritePlaceRequest createFavoritePlaceRequest) {
        checkIfPlaceExists(createFavoritePlaceRequest.getPlaceId());
        checkIfUserExists(createFavoritePlaceRequest.getUserId());
        if (favoritePlaceRepository.existsFavoritePlace(createFavoritePlaceRequest.getPlaceId(), createFavoritePlaceRequest.getUserId())) {
            throw new FavoritePlaceAlreadyExistsException("The venue has already been added to favorites");
        }
        FavoritePlace favoritePlace = modelMapperService.forRequest().map(createFavoritePlaceRequest, FavoritePlace.class);
        favoritePlaceRepository.save(favoritePlace);
        return createFavoritePlaceRequest;
    }

    public List<FavoritePlaceDto> getByUserId(Long id) {
        checkIfUserExists(id);
        List<FavoritePlace> favoritePlaces = favoritePlaceRepository.getByUserId(id);

        List<FavoritePlaceDto> response = favoritePlaces.stream()
                .map(favoritePlace -> modelMapperService.forDto().map(favoritePlace, FavoritePlaceDto.class))
                .collect(Collectors.toList());
        return response;
    }

    public void delete(Long id) {
        checkIfCommentExists(id);
        favoritePlaceRepository.deleteById(id);
    }

    private void checkIfCommentExists(Long id) {
        if (!favoritePlaceRepository.existsById(id)) {
            throw new CommentNotFoundException("Favorite places not found");
        }
    }

    private void checkIfPlaceExists(Long id) {
        placeService.checkIfPlaceExists(id);
    }

    private void checkIfUserExists(Long id) {
        userService.existsById(id);
    }
}
