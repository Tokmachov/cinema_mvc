package edu.school21.cinema.services;

import edu.school21.cinema.controllers.params.MovieSessionParams;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.MovieHall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TestDataService {
    @Value("${test_data_required:false}")
    private boolean isTestDataRequired;
    private MovieService movieService;
    private MovieHallService movieHallService;
    private MovieSessionService movieSessionService;

    public TestDataService(MovieService movieService,
                           MovieHallService movieHallService,
                           MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.movieHallService = movieHallService;
        this.movieSessionService = movieSessionService;
    }

    @PostConstruct
    @Transactional
    public void createTestData() {
        if (isTestDataRequired) {
            try {
                List<Movie> movieList = createTestMovies();
                MovieHall movieHall = createTestMovieHall();
                for (Movie movie : movieList) {
                    movieSessionService.saveMovieSession(new MovieSessionParams("2022-12-29T11:19", movieHall.getId(), movie.getTitle()));
                    movieSessionService.saveMovieSession(new MovieSessionParams("2022-12-29T12:19", movieHall.getId(), movie.getTitle()));
                    movieSessionService.saveMovieSession(new MovieSessionParams("2022-12-29T13:19", movieHall.getId(), movie.getTitle()));
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private List<Movie> createTestMovies() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("test_posters");
        List<Movie> movieList = Stream.of(new File(url.getPath()).listFiles())
                .filter(file -> !file.isDirectory())
                .map(file -> {
                    try {
                        Movie movie = createTestMove(file);
                        InputStream is = new FileInputStream(file);
                        return movieService.save(movie, is);
                    } catch (Exception ex) {
                        throw new IllegalArgumentException("Failed to create test movie: " + ex.getMessage());
                    }
                }).collect(Collectors.toList());
        return movieList;
    }

    private Movie createTestMove(File poster) {
        int year = new Random().nextInt(2022 - 1940 + 1) + 1940;
        int ageRestriction = new Random().nextInt(18 - 8 + 1) + 8;
        int descriptionLength = new Random().nextInt(150 - 50 + 1) + 100;
        String description = createRandomString(descriptionLength);
        return new Movie(poster.getName(), year, ageRestriction, description, UUID.randomUUID().toString());
    }

    private String createRandomString(int length) {
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = (byte) (new Random().nextInt(91 - 65 + 1) + 65);
        }
        return new String(array, Charset.forName("UTF-8"));
    }

    private MovieHall createTestMovieHall() {
        return movieHallService.save(new MovieHall("Saturn hall", 20));
    }
}
