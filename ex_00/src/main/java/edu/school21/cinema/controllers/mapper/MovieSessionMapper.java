package edu.school21.cinema.controllers.mapper;

import edu.school21.cinema.controllers.dto.MovieSessionDto;
import edu.school21.cinema.models.MovieSession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MovieSessionMapper {
    public static MovieSessionDto toDto(MovieSession movieSession) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(movieSession.getTimeStart()), ZoneId.systemDefault());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("LLL - yyyy");
        String time = localDateTime.format(timeFormatter);
        String date = localDateTime.format(dateFormatter);
        return new MovieSessionDto(movieSession.getId(), date, time, movieSession.getMovie().getTitle(), movieSession.getMovieHall().getId());
    }
}
