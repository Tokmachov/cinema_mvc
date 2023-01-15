package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieChatMessage;
import edu.school21.cinema.services.MovieChatMessageService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieChatMessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public MovieChatMessage save(MovieChatMessage movieChatMessage) {
        entityManager.persist(movieChatMessage);
        return movieChatMessage;
    }

    public List<MovieChatMessage> findLastOrderedByDate(long movieId, int lastMessagesCount) {
        long messageCount = (long) entityManager.createQuery("select count(movieChatMessage) from MovieChatMessage movieChatMessage").getSingleResult();
        TypedQuery<MovieChatMessage> movieChatMessageTypedQuery = entityManager.createQuery("select movieChatMessage from MovieChatMessage  movieChatMessage where movieChatMessage.movie.id = ?1 order by movieChatMessage.timeStamp asc", MovieChatMessage.class);
        int firstResultIndex = (int)messageCount > lastMessagesCount
                ? (int)messageCount - lastMessagesCount
                : 0;
        movieChatMessageTypedQuery.setFirstResult(firstResultIndex);
        movieChatMessageTypedQuery.setParameter(1, movieId);
        return movieChatMessageTypedQuery.getResultList();
    }
}
