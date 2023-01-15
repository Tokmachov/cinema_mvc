package edu.school21.cinema.services;

import edu.school21.cinema.models.MovieChatMessage;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.MovieChatMessageRepository;
import edu.school21.cinema.repositories.MovieRepository;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class MovieChatMessageService {
    private MovieChatMessageRepository movieChatMessageRepository;
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    public MovieChatMessageService(MovieChatMessageRepository movieChatMessageRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.movieChatMessageRepository = movieChatMessageRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MovieChatMessage save(String userName, String movieId, String message) {
        User user = userRepository.findById(userName).orElseThrow(() -> new IllegalArgumentException("User is absent"));
        Movie movie = movieRepository.findById(Long.parseLong(movieId));
        long timeStamp = Instant.now().toEpochMilli();
        MovieChatMessage movieChatMessage = new MovieChatMessage(message, timeStamp, user, movie);
        return movieChatMessageRepository.save(movieChatMessage);
    }

    public List<MovieChatMessage> findLastOrderedByDate(long movieId, int lastMessagesCount) {
        return movieChatMessageRepository.findLastOrderedByDate(movieId, lastMessagesCount);
    }
}
