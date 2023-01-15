package edu.school21.cinema.services;

import edu.school21.cinema.controllers.utils.DirectoryUtils;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.repositories.MovieRepository;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {

    @Getter
    @Value("${poster.path:./pictures/posters}")
    private String posterPath;

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie, InputStream poster) throws IOException {
        File targetFile = new File(posterPath, movie.getPosterId());
        FileUtils.copyInputStreamToFile(poster, targetFile);
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public void deleteByMovieTitleList(List<String> movieTileList) {
        for (String movieTitle : movieTileList) {
            Movie movie = movieRepository.findByTitle(movieTitle);
            movieRepository.deleteByTitle(movieTitle);
            deletePoster(movie.getPosterId());
        }
    }

    private void deletePoster(String id) {
        File poster = new File(posterPath + "/" + id);
        poster.delete();
    }

    public Movie findById(long id) {
        return movieRepository.findById(id);
    }
}
