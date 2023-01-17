package edu.school21.cinema.controllers.mapper;

import edu.school21.cinema.controllers.dto.MovieSessionDetailsDto;
import edu.school21.cinema.controllers.dto.MovieSessionDto;
import edu.school21.cinema.controllers.dto.SearchMovieSessionDto;
import edu.school21.cinema.controllers.utils.TimeUtils;
import edu.school21.cinema.models.MovieSession;
import org.apache.commons.lang3.tuple.Pair;

public class MovieSessionMapper {
    public static MovieSessionDto toMovieSessionDto(MovieSession movieSession) {
        Pair<String, String> dateAndTime = TimeUtils.toDateAndTime(movieSession.getTimeStart());
        return new MovieSessionDto(movieSession.getId(), dateAndTime.getLeft(), dateAndTime.getRight(), movieSession.getMovie().getTitle(), movieSession.getMovieHall().getId(), movieSession.getPrice());
    }

    public static SearchMovieSessionDto toSearchSessionDto(MovieSession movieSession) {
        long sessionId = movieSession.getId();
        String posterId = movieSession.getMovie().getPosterId();
        String movieTitle = movieSession.getMovie().getTitle();
        String movieHallTitle = movieSession.getMovieHall().getId();
        Pair<String, String> dateAndTime = TimeUtils.toDateAndTime(movieSession.getTimeStart());
        return new SearchMovieSessionDto(sessionId, posterId, movieTitle, movieHallTitle, dateAndTime.getLeft(), dateAndTime.getRight());
    }

    public static MovieSessionDetailsDto toMovieSessionDetailsDto(MovieSession movieSession) {
        return new MovieSessionDetailsDto(
          movieSession.getMovieHall().getId(),
          movieSession.getMovieHall().getNumberOfSeats(),
          TimeUtils.toDateAndTime(movieSession.getTimeStart()).getLeft(),
          TimeUtils.toDateAndTime(movieSession.getTimeStart()).getRight(),
          movieSession.getMovie().getTitle(),
          movieSession.getMovie().getYearCreated(),
          movieSession.getMovie().getAgeRestriction(),
          movieSession.getMovie().getPosterId(),
          movieSession.getMovie().getDescription()
        );
    }
}
