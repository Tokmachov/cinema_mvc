package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieHall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieHallRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(MovieHall movieHall) {
        em.merge(movieHall);
    }

    public List<MovieHall> findAll() {
        return em.createQuery("select movieHall from MovieHall movieHall", MovieHall.class).getResultList();
    }
    public void deleteById(String id) {
        MovieHall movieHall = em.find(MovieHall.class, id);
        em.remove(movieHall);
    }
    public void deleteByIdList(List<String> idList) {
        Query q = em.createQuery("delete from MovieHall movieHall where movieHall.id in (?1)");
        q.setParameter(1, idList);
        q.executeUpdate();
    }
}
