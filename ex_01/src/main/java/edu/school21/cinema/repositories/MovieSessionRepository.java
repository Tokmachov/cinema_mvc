package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieSession;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieSessionRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(MovieSession movieSession) {
        em.merge(movieSession);
    }

    public List<MovieSession> findAll() {
        return em.createQuery("select movieSession from MovieSession movieSession", MovieSession.class).getResultList();
    }

    public void deleteByIds(List<Long> idList) {
        Query mq = em.createQuery("delete from MovieSession m where m.id in (?1)");
        mq.setParameter(1, idList);
        mq.executeUpdate();
    }
}
