package com.where.where;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.where.where.dto.CommentDto;
import com.where.where.dto.CreateCommentRequest;
import com.where.where.dto.CreateScoreRequest;
import com.where.where.dto.PlaceDto;
import com.where.where.dto.ScoreDto;
import com.where.where.model.Category;
import com.where.where.model.Comment;
import com.where.where.model.Location;
import com.where.where.model.Owner;
import com.where.where.model.Place;
import com.where.where.model.PlaceCategory;
import com.where.where.model.Score;
import com.where.where.model.User;

public class TestSupport {

    // Generate Categories
    public static List<PlaceCategory> generateCategories() {
        return IntStream.range(0, 5).mapToObj(i -> new PlaceCategory((long) i, new Place(), new Category()))
                .collect(Collectors.toList());
    }

    // Place
    public static Place generatePlace() {
        Place place = new Place(1L, "Cafe Onur", "Pazartesi-Cuma", "09:00-23:45", "123123123",
                new Location(1L, 15, 23, "Türkiye", "Eskişehir", "Büyükdere mahallesi", new ArrayList<>()),
                new ArrayList<>(), new Owner(), new ArrayList<>(), new ArrayList<>());
        return place;
    }

    public static PlaceDto generatePlaceDto(Place place) {
        PlaceDto placeDto = new PlaceDto(1L, place.getWorkDays(), place.getWorkHours(), place.getPlaceName(), place.getPhoneNumber(),
                place.getLocation().getId(), place.getLocation().getCountry(), place.getLocation().getCity(),
                place.getLocation().getAddress(), new ArrayList<>(), place.getOwner().getId(),
                place.getOwner().getUsername());
        return placeDto;
    }

    // Score
    public static CreateScoreRequest generateCreateScoreRequest() {
        CreateScoreRequest createScoreRequest = new CreateScoreRequest(3, 5, LocalDate.now(), 1L, 1L);
        return createScoreRequest;
    }

    public static Score generateScore() {
        Score score = new Score(1L, 3, 3, LocalDate.now(),
                new User("Onur", "Akkepenek", new ArrayList<>(), new ArrayList<>()), new Place(1L, "Cafe Onur",
                "Pazartesi-Cuma", "08:00-24:00", "123123123", null, null, null, new ArrayList<>(), new ArrayList<>()));
        return score;
    }

    public static Score generateScore(CreateScoreRequest createScoreRequest) {
        Score score = new Score(1L, createScoreRequest.getVenueScore(), createScoreRequest.getCoronaScore(),
                createScoreRequest.getCreateDate(), new User("Onur", "Akkepenek", new ArrayList<>(), new ArrayList<>()),
                new Place(1L, "Cafe Onur", "Pazartesi-Cuma", "08:00-24:00", "123123123", null, null, null, new ArrayList<>(),
                        new ArrayList<>()));
        return score;
    }

    public static ScoreDto generateScoreDto(Score score) {

        ScoreDto scoreDto = new ScoreDto(score.getId(), score.getVenueScore(), score.getCoronaScore(),
                score.getCreateDate(), score.getUser().getFirstName(), score.getUser().getLastName(),
                score.getPlace().getPlaceName());
        return scoreDto;
    }

    public static List<ScoreDto> generateScoreDtoList(List<Score> scoreList) {
        return scoreList.stream()
                .map(from -> new ScoreDto(from.getId(), from.getVenueScore(), from.getCoronaScore(),
                        from.getCreateDate(), from.getUser().getFirstName(), from.getUser().getLastName(),
                        from.getPlace().getPlaceName()))
                .collect(Collectors.toList());
    }

    // Comment
    public static CreateCommentRequest generateCreateCommentRequest() {
        CreateCommentRequest createCommentRequest = new CreateCommentRequest("Hizmetiniz çok iyi",
                LocalDate.of(2022, 2, 21), 1L, 1L);
        return createCommentRequest;
    }

    public static Comment generateComment(CreateCommentRequest createCommentRequest) {
        Comment comment = new Comment(1L, createCommentRequest.getCommentText(), createCommentRequest.getCreateDate(),
                new User("Onur", "Akkepenek", new ArrayList<>(), new ArrayList<>()), new Place(1L, "Cafe Onur",
                "Pazartesi-Cuma", "08:00-24:00", "123123123", null, null, null, new ArrayList<>(), new ArrayList<>()));
        return comment;
    }

    public static Comment generateComment() {
        Comment comment = new Comment(1L, "Merhaba ben onur", LocalDate.of(2022, 3, 22),
                new User("Onur", "Akkepenek", new ArrayList<>(), new ArrayList<>()), new Place(1L, "Cafe Onur",
                "Pazartesi-Cuma", "08:00-24:00", "123123123", null, null, null, new ArrayList<>(), new ArrayList<>()));
        return comment;
    }

    public static CommentDto generateCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto(comment.getId(), comment.getCommentText(), comment.getCreateDate(),
                comment.getUser().getFirstName(), comment.getUser().getLastName(), comment.getPlace().getPlaceName());
        return commentDto;
    }

}
