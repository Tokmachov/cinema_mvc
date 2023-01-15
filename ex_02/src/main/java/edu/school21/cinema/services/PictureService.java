package edu.school21.cinema.services;

import edu.school21.cinema.models.UserPicture;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.PictureRepository;
import edu.school21.cinema.repositories.UserRepository;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class PictureService {
    @Getter
    @Value("${user.userPicture.path:./pictures/user_picture}")
    private String userPicturePath;

    public PictureService(UserRepository userRepository, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }

    private UserRepository userRepository;
    private PictureRepository pictureRepository;

    @Transactional
    public User addPicture(String userName, InputStream pictureIs, String pictureName) throws IOException {
        User user = userRepository.findById(userName).orElseThrow(() -> new IllegalArgumentException("User is absent in database. User name: " + userName));
        String pictureId = UUID.randomUUID().toString();
        File targetFile = new File(userPicturePath, pictureId);
        FileUtils.copyInputStreamToFile(pictureIs, targetFile);

        UserPicture userPicture = pictureRepository.save(new UserPicture(pictureId, pictureName, user));
        user.setCurrentUserPicture(userPicture);
        user.getUserPictureList().add(userPicture);
        user.getUserPictureList().size();
        return user;
    }
}
