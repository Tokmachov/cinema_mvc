package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserPicture;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PictureRepository {
    @PersistenceContext
    private EntityManager em;

    public UserPicture save(UserPicture userPicture) {
        em.persist(userPicture);
        return userPicture;
    }
}
