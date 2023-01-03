package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieRepository {
    @PersistenceContext
    private EntityManager em;

    public Movie save(Movie movie) {
        return em.merge(movie);
    }

    public List<Movie> findAll() {
        return em.createQuery("select movie from Movie movie", Movie.class).getResultList();
    }

    public Movie findByTitle(String title) {
        TypedQuery<Movie> movieTypedQuery = em.createQuery("select movie from Movie movie where movie.title = ?1", Movie.class);
        movieTypedQuery.setParameter(1, title);
        return movieTypedQuery.getResultList().get(0);
    }

    public void deleteByTitle(String title) {
        Movie movie = findByTitle(title);
        em.remove(movie);
    }

    public Movie findById(String id) {
        return em.find(Movie.class, id);
    }
}
