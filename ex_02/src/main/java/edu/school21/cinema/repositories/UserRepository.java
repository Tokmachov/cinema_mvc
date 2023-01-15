package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class UserRepository {
    @Getter
    @PersistenceContext
    private EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> findByIdLoadWithPictures(String id) {
        EntityGraph entityGraph = em.getEntityGraph("user-userPicture-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.loadgraph", entityGraph);
        return Optional.ofNullable(em.find(User.class, id, properties));
    }
}
