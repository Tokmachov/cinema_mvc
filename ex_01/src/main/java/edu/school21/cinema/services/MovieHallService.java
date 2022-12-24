package edu.school21.cinema.services;

import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.repositories.MovieHallRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieHallService {
    private MovieHallRepository movieHallRepository;

    public MovieHallService(MovieHallRepository movieHallRepository) {
        this.movieHallRepository = movieHallRepository;
    }

    public List<MovieHall> get() {
        return movieHallRepository.findAll();
    }

    public void add(MovieHall movieHall) {
        movieHallRepository.save(movieHall);
    }

    public void deleteById(String id) {
        movieHallRepository.deleteById(id);
    }

    public void deleteByIdList(List<String> idList) {
        movieHallRepository.deleteByIdList(idList);
    }
}
