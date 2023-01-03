package edu.school21.cinema.services;

import edu.school21.cinema.controllers.params.MovieSessionParams;
import edu.school21.cinema.controllers.utils.TimeUtils;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.models.MovieSession;
import edu.school21.cinema.repositories.MovieHallRepository;
import edu.school21.cinema.repositories.MovieRepository;
import edu.school21.cinema.repositories.MovieSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MovieSessionService {
    private MovieSessionRepository movieSessionRepository;
    private MovieRepository movieRepository;
    private MovieHallRepository movieHallRepository;

    public MovieSessionService(MovieSessionRepository movieSessionRepository, MovieRepository movieRepository, MovieHallRepository movieHallRepository) {
        this.movieSessionRepository = movieSessionRepository;
        this.movieRepository = movieRepository;
        this.movieHallRepository = movieHallRepository;
    }

    public void saveMovieSession(MovieSessionParams movieSessionParams) {
        Movie movie = movieRepository.findByTitle(movieSessionParams.getMovieTitle());
        MovieHall movieHall = movieHallRepository.findById(movieSessionParams.getMovieHallId());

        LocalDateTime localDateTime = LocalDateTime.parse(movieSessionParams.getDateAndTime());
        ZoneId zoneId = ZoneId.systemDefault();
        long timeStart = localDateTime.atZone(zoneId).toInstant().toEpochMilli();
        MovieSession movieSession = new MovieSession(timeStart, movie, movieHall);
        movieSessionRepository.save(movieSession);
    }

    public List<MovieSession> findAll() {
        return movieSessionRepository.findAll();
    }

    public void deleteByIds(List<Long> idList) {
        movieSessionRepository.deleteByIds(idList);
    }

    public List<MovieSession> findMovieByTitleLike(String query) {
        return movieSessionRepository.findByMovieTitleLike(query);
    }

    public MovieSession findById(long id) {
        return movieSessionRepository.findById(id);
    }
}
