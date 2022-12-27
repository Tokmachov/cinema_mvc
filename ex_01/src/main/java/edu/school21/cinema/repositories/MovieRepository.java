package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Movie movie) {
        em.merge(movie);
    }

    public List<Movie> findAll() {
        return em.createQuery("select movie from Movie movie", Movie.class).getResultList();
    }

    public Movie findById(String id) {
        return em.find(Movie.class, id);
    }

    public void deleteById(String id) {
        Movie movie = em.find(Movie.class, id);
        em.remove(movie);
    }
}
