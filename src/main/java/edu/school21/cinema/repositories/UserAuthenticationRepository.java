package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserAuthentication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserAuthenticationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(UserAuthentication userAuthentication) {
        entityManager.persist(userAuthentication);
    }

    public List<UserAuthentication> findAllByUserId(String userId) {
        TypedQuery<UserAuthentication> userAuthenticationTypedQuery = entityManager.createQuery("select userAuthentication from UserAuthentication userAuthentication where userAuthentication.user.name = ?1", UserAuthentication.class);
        userAuthenticationTypedQuery.setParameter(1, userId);
        return userAuthenticationTypedQuery.getResultList();
    }
}
