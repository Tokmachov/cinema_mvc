package edu.school21.cinema.controllers.mapper;

import edu.school21.cinema.controllers.dto.MovieSessionDetailsDto;
import edu.school21.cinema.controllers.dto.MovieSessionDto;
import edu.school21.cinema.controllers.dto.SearchMovieSessionDto;
import edu.school21.cinema.models.MovieSession;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MovieSessionMapper {
    public static MovieSessionDto toMovieSessionDto(MovieSession movieSession) {
        Pair<String, String> dateAndTime = toDateAndTime(movieSession.getTimeStart());
        return new MovieSessionDto(movieSession.getId(), dateAndTime.getLeft(), dateAndTime.getRight(), movieSession.getMovie().getTitle(), movieSession.getMovieHall().getId());
    }

    public static SearchMovieSessionDto toSearchSessionDto(MovieSession movieSession) {
        long sessionId = movieSession.getId();
        String movieId = movieSession.getMovie().getId();
        String posterId = movieSession.getMovie().getPosterId();
        String movieTitle = movieSession.getMovie().getTitle();
        String movieHallTitle = movieSession.getMovieHall().getId();
        Pair<String, String> dateAndTime = toDateAndTime(movieSession.getTimeStart());
        return new SearchMovieSessionDto(sessionId, movieId, posterId, movieTitle, movieHallTitle, dateAndTime.getLeft(), dateAndTime.getRight());
    }

    private static Pair<String, String> toDateAndTime(long timeStamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("LLL - yyyy");
        String date = localDateTime.format(dateFormatter);
        String time = localDateTime.format(timeFormatter);
        return Pair.of(date, time);
    }

    public static MovieSessionDetailsDto toMovieSessionDetailsDto(MovieSession movieSession) {
        return new MovieSessionDetailsDto(
          movieSession.getMovieHall().getId(),
          movieSession.getMovieHall().getNumberOfSeats(),
          toDateAndTime(movieSession.getTimeStart()).getLeft(),
          toDateAndTime(movieSession.getTimeStart()).getRight(),
          movieSession.getMovie().getTitle(),
          movieSession.getMovie().getYearCreated(),
          movieSession.getMovie().getAgeRestriction(),
          movieSession.getMovie().getPosterId(),
          movieSession.getMovie().getDescription()
        );
    }
}
