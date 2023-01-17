package edu.school21.cinema.services;

import edu.school21.cinema.models.UserAuthentication;
import edu.school21.cinema.repositories.UserAuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthenticationService {
    private UserAuthenticationRepository userAuthenticationRepository;

    public UserAuthenticationService(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    public void save(UserAuthentication userAuthentication) {
        userAuthenticationRepository.save(userAuthentication);
    }

    public List<UserAuthentication> findAllByUserId(String userId) {
        return userAuthenticationRepository.findAllByUserId(userId);
    }
}
